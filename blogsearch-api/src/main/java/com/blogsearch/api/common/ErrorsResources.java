package com.blogsearch.api.common;

import org.springframework.hateoas.EntityModel;
import org.springframework.validation.Errors;

public class ErrorsResources extends EntityModel<Errors> {

    public static EntityModel<Errors> modelOf(Errors errors) {
        EntityModel<Errors> errorsModel = EntityModel.of(errors);
        return errorsModel;
    }
}
