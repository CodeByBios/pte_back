package com.sodifrance.pte.commun.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PteCollectionUtils {

    private PteCollectionUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Transforme une liste en une autre liste déterminé par la fonction en entrée.
     *
     * @param <T> the generic type
     * @param <U> the generic type
     * @param coll the coll
     * @param function the function
     * @return the list on collection
     */
    public static <T, U> List<U> transformCollectionToList(Collection<T> coll, Function<T, U> function) {
        return coll.stream().map(function).collect(Collectors.toList());
    }

    /**
     * Création d'un predicate permettant de faire un distinct par rapport à une fontion.
     *
     * @param <T> the generic type
     * @param keyExtractor the key extractor
     * @return the predicate
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        final Set<Object> seen = new HashSet<>();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
