package de.slothsoft.blaupause;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import de.slothsoft.blaupause.contrib.ExampleContrib;

/**
 * Util class for getting and managing {@link Contrib}s (hence the name)
 *
 * @since 1.0.0
 */

public final class Contribs {

	/**
	 * Returns all instances of {@link Contrib}s in the <code>contrib</code> package.
	 *
	 * @return a list of implementations
	 */

	public static List<Contrib> fetchAllImplementations() {
		return fetchImplementations(ExampleContrib.class.getPackage());
	}

	/**
	 * Returns all instances of {@link Contrib}s in a specified package.
	 *
	 * @param searchedPackage - the package to be searched
	 * @return a list of implementations
	 */

	static List<Contrib> fetchImplementations(Package searchedPackage) {
		try {
			final List<Contrib> contribs = new ArrayList<>();
			final List<Class<?>> classes = getClasses(searchedPackage.getName());
			for (final Class<?> clazz : classes) {
				if (!Modifier.isAbstract(clazz.getModifiers()) && Contrib.class.isAssignableFrom(clazz)) {
					contribs.add((Contrib) clazz.newInstance());
				}
			}
			return contribs;
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns all implementations of {@link Contrib}s in a specified. package.
	 *
	 * @param packageName - the package to be searched (e.g. "de.slothsoft.game")
	 * @return a list of classes
	 */

	static List<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException {
		final List<Class<?>> classes = new ArrayList<>();
		final String path = packageName.replace('.', '/');

		try {
			final URL resource = Contribs.class.getResource('/' + path);
			if (resource == null) return classes;
			final URI uri = resource.toURI();

			Path[] pathToFolders;
			if (uri.getScheme().equals("jar")) {
				// we are in the application JAR
				final FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
				pathToFolders = new Path[]{fileSystem.getPath('/' + path)};
			} else {
				// we are in the IDE
				final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				final List<URL> resources = Collections.list(classLoader.getResources(path));
				pathToFolders = new Path[resources.size()];
				for (int i = 0; i < pathToFolders.length; i++) {
					pathToFolders[i] = Paths.get(resources.get(i).toURI());
				}
			}
			for (final Path pathToFolder : pathToFolders) {
				classes.addAll(findClasses(pathToFolder, packageName));
			}

		} catch (final URISyntaxException e) {
			throw new RuntimeException("This really should not have happend o_O", e);
		}
		return classes;
	}

	private static List<Class<?>> findClasses(Path directory, String packageName)
			throws ClassNotFoundException, IOException {
		final List<Class<?>> classes = new ArrayList<>();
		try (Stream<Path> walker = Files.walk(directory, Integer.MAX_VALUE)) {
			for (final Iterator<Path> it = walker.iterator(); it.hasNext();) {
				final Path file = it.next();
				if (!Files.isDirectory(file)) {
					final String fileName = file.getFileName().toString();
					if (fileName.endsWith(".class")) {
						classes.add(Class.forName(packageName + '.' + fileName.substring(0, fileName.length() - 6)));
					}
				}
			}
		}
		return classes;
	}

	private Contribs() {
		// hide me
	}
}
