package org.leon.finch.service.script;


import javax.tools.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Leon Song
 * @date 2021-05-29
 */
public class ScriptFileManager implements JavaFileManager {

    private StandardJavaFileManager fileManager;
    private Map<String, ByteArrayOutputStream> buffers = new LinkedHashMap<>();

    public ScriptFileManager(StandardJavaFileManager fileManager) {
        this.fileManager = fileManager;
    }

    public Map<String, byte[]> getAllBuffers() {
        LinkedHashMap<String, byte[]> res = new LinkedHashMap<>(this.buffers.size() * 2);

        for (Map.Entry<String, ByteArrayOutputStream> entry : this.buffers.entrySet()) {
            res.put(entry.getKey(), entry.getValue().toByteArray());
        }
        return res;
    }

    @Override
    public ClassLoader getClassLoader(Location location) {
        return this.fileManager.getClassLoader(location);
    }

    @Override
    public Iterable<JavaFileObject> list(Location location, String packageName, Set<JavaFileObject.Kind> kinds, boolean recurse) throws IOException {
        return fileManager.list(location, packageName, kinds, recurse);
    }

    @Override
    public String inferBinaryName(Location location, JavaFileObject file) {
        return fileManager.inferBinaryName(location, file);
    }

    @Override
    public boolean isSameFile(FileObject a, FileObject b) {
        return fileManager.isSameFile(a, b);
    }

    @Override
    public boolean handleOption(String current, Iterator<String> remaining) {
        return fileManager.handleOption(current, remaining);
    }

    @Override
    public boolean hasLocation(Location location) {
        return fileManager.hasLocation(location);
    }

    @Override
    public JavaFileObject getJavaFileForInput(Location location, String className, JavaFileObject.Kind kind) throws IOException {

        if (location == StandardLocation.CLASS_OUTPUT && buffers.containsKey(className) && kind == JavaFileObject.Kind.CLASS) {

            byte[] bytes = buffers.get(className).toByteArray();

            return new SimpleJavaFileObject(URI.create(className), kind) {

                @Override
                public InputStream openInputStream() {
                    return new ByteArrayInputStream(bytes);
                }
            };

        }

        return fileManager.getJavaFileForInput(location, className, kind);
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        return new SimpleJavaFileObject(URI.create(className), kind) {
            @Override
            public OutputStream openOutputStream() {
                ByteArrayOutputStream data = new ByteArrayOutputStream();
                buffers.put(className, data);
                return data;
            }
        };
    }

    @Override
    public FileObject getFileForInput(Location location, String packageName, String relativeName) throws IOException {
        return fileManager.getFileForInput(location, packageName, relativeName);
    }

    @Override
    public FileObject getFileForOutput(Location location, String packageName, String relativeName, FileObject sibling) throws IOException {
        return fileManager.getFileForOutput(location, packageName, relativeName, sibling);
    }

    @Override
    public void flush() throws IOException {
        fileManager.flush();
    }

    @Override
    public void close() throws IOException {
        fileManager.close();
    }

    @Override
    public int isSupportedOption(String option) {
        return fileManager.isSupportedOption(option);
    }

    public String inferModuleName(Location location) throws IOException {
        return this.invokeNamedMethodIfAvailable(location, "inferModuleName");
    }


    public Iterable<Set<Location>> listLocationsForModules(Location location) throws IOException {
        return this.invokeNamedMethodIfAvailable(location, "listLocationsForModules");
    }

    private <T> T invokeNamedMethodIfAvailable(Location location, String name) {

        Method[] declaredMethods = this.fileManager.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {

            if (method.getName().equals(name) && method.getParameterTypes().length == 1 &&
                    method.getParameterTypes()[0] == Location.class) {

                try {
                    return (T) method.invoke(fileManager, location);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new UnsupportedOperationException("Unable to invoke method " + name);
                }
            }
        }
        throw new UnsupportedOperationException("Unable to invoke method " + name);
    }
}
