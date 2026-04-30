package com.carlos.minicursorocketseatjava.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;


public class Utils {

    // Copia só os campos NÃO nulos do source para o target
    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    // Retorna um array com os nomes dos campos nulos
    public static String[] getNullPropertyNames(Object source) {


        final BeanWrapper src = new BeanWrapperImpl(source);


        PropertyDescriptor[] pds = src.getPropertyDescriptors();


        Set<String> emptyNames = new HashSet<>();


        for (PropertyDescriptor pd: pds) {
            Object srcValue = src.getPropertyValue(pd.getName());

            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        // Converte o Set para array e retorna
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}