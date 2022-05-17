package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.FavoriteConverter;
import actions.views.FavoriteView;
import models.validators.FavoriteValidator;

public class FavoriteService extends ServiceBase {

    public List<String> create(FavoriteView fv) {
        List<String> errors = FavoriteValidator.validate(fv);
        if (errors.size() == 0) {
            LocalDateTime ldt = LocalDateTime.now();
            fv.setCreatedAt(ldt);
            fv.setUpdatedAt(ldt);
            createInternal(fv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    private void createInternal(FavoriteView fv) {

        em.getTransaction().begin();
        em.persist(FavoriteConverter.toModel(fv));
        em.getTransaction().commit();

    }



}
