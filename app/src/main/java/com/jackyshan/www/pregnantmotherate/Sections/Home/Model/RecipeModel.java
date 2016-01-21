package com.jackyshan.www.pregnantmotherate.Sections.Home.Model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.jackyshan.www.pregnantmotherate.General.Base.BaseModel;

/**
 * Created by jackyshan on 1/21/16.
 */
@Table(name = "CB_WomanRecipe")
public class RecipeModel extends BaseModel{
    @Column(name = "id")
    public int recipeId;

    @Column(name = "star")
    public boolean star;

    @Column(name = "name")
    public String name;

    @Column(name = "ingredients")
    public String ingredients;

    @Column(name = "steps")
    public String steps;

    @Column(name = "photo")
    public String photo;

    @Column(name = "month")
    public String month;

    @Column(name = "suberName")
    public String suberName;

    public RecipeModel() {
        super();
    }
}
