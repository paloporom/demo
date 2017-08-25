package com.example.demo.domain.entity;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PostDeleteContext;
import org.seasar.doma.jdbc.entity.PostInsertContext;
import org.seasar.doma.jdbc.entity.PostUpdateContext;
import org.seasar.doma.jdbc.entity.PreDeleteContext;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;

/**
 * 
 */
public class PrefectureListener implements EntityListener<Prefecture> {

    @Override
    public void preInsert(Prefecture entity, PreInsertContext<Prefecture> context) {
    }

    @Override
    public void preUpdate(Prefecture entity, PreUpdateContext<Prefecture> context) {
    }

    @Override
    public void preDelete(Prefecture entity, PreDeleteContext<Prefecture> context) {
    }

    @Override
    public void postInsert(Prefecture entity, PostInsertContext<Prefecture> context) {
    }

    @Override
    public void postUpdate(Prefecture entity, PostUpdateContext<Prefecture> context) {
    }

    @Override
    public void postDelete(Prefecture entity, PostDeleteContext<Prefecture> context) {
    }
}