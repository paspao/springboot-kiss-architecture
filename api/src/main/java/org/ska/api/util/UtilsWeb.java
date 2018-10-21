package org.ska.api.util;

import org.ska.api.web.beans.ErrorMessage;
import org.ska.business.utils.UtilsBusiness;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by <a href="mailto:pasquale.paola@eng.it">Pasquale Paola</a> on 08/10/18.
 */
public class UtilsWeb extends UtilsBusiness {


    public static ResponseEntity<ErrorMessage> writeError(Logger logger, HttpStatus statusCode, Throwable throwable)
    {
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setMessage(throwable.getMessage());
        logger.error(throwable.getMessage(),throwable);
        ResponseEntity<ErrorMessage> errorMessageResponseEntity=new ResponseEntity(errorMessage,statusCode);
        return errorMessageResponseEntity;
    }

}
