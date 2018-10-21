package org.ska.business.utils;

import java.util.Collection;

/**
 * Created by <a href="mailto:pasquale.paola@eng.it">Pasquale Paola</a> on 08/10/18.
 */
public class UtilsBusiness {

    public static boolean isEmpty(String string) {
        return (string != null && string.trim().length() > 0) ? false : true;
    }

    public static boolean isEmpty(Collection<?> collection) {

        boolean result;

        if (collection == null || collection.isEmpty()) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }
}
