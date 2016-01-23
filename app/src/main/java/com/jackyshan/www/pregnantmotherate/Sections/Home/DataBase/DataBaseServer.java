package com.jackyshan.www.pregnantmotherate.Sections.Home.DataBase;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Model.RecipeModel;

import java.util.List;

/**
 * Created by jackyshan on 1/21/16.
 */
public class DataBaseServer {

    public static List<RecipeModel> selectRecipes(String month) {
        return  new Select()
                .from(RecipeModel.class)
                .where("month = ?", month)
                .execute();
    }

    public static void updateStarRecipe(RecipeModel model) {
        RecipeModel recipeModel = RecipeModel.load(RecipeModel.class, model.id);
        recipeModel.star = model.star;
        recipeModel.save();//数据库更新
    }

    public static List<RecipeModel> selectStarRecipes() {
        return  new Select()
                .from(RecipeModel.class)
                .where("star = ?", true)
                .execute();
    }

    public static List<RecipeModel> selectSearchRecipes(String text) {
        text = new String('%' + text + '%');
        return  new Select()
                .from(RecipeModel.class)
                .where("name LIKE ? OR suberName LIKE ? OR steps LIKE ? OR ingredients LIKE ?", text, text, text, text)
                .execute();
    }
}
