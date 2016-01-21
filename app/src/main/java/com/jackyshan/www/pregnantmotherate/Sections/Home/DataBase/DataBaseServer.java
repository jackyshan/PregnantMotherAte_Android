package com.jackyshan.www.pregnantmotherate.Sections.Home.DataBase;

import com.activeandroid.query.Select;
import com.jackyshan.www.pregnantmotherate.Sections.Home.Model.RecipeModel;

import java.util.List;

/**
 * Created by jackyshan on 1/21/16.
 */
public class DataBaseServer {

    public static List<RecipeModel> selectRecipes(String month) {
        return  new Select()
                .from(RecipeModel.class)
//                .where("month = ?", month)
                .execute();
    }
}
