package com.sodifrance.pte.commun.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The Class GpecMapUtils.<br>
 * Cette classe est une classe utilitaire sur les map.
 */
public class PteMapUtils {

    private PteMapUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Transforme une liste en une map.
     *
     * @param <T> Type de la clé
     * @param <U> Type de la liste
     * @param list Liste à tranformer
     * @param function function permettant de calculer la clé
     * @return the map
     */
    public static <T, U> Map<T, U> transform(List<U> list, Function<U, T> function) {
        // Function.identity() retourne l'instance U
        return list.stream().collect(Collectors.toMap(function, Function.identity()));
    }

    /**
     * Transforme une liste en une map.
     *
     * @param <T> Type de la clé
     * @param <U> Type du bean de la collection
     * @param <V> Type de la valeur
     * @param list Liste à tranformer
     * @param functionKey function permettant de calculer la clé
     * @param function function permettant de calculer la valeur
     * @return the map
     */
    public static <T, U, V> Map<T, V> transform(Set<U> list, Function<U, T> functionKey, Function<U, V> function) {
        // Function.identity() retourne l'instance U
        return list.stream().collect(Collectors.toMap(functionKey, function));
    }
}
