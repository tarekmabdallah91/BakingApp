/*
 Copyright 2018 tarekmabdallah91@gmail.com

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.gmail.tarekmabdallah91.bakingapp.data.room.recipe;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.gmail.tarekmabdallah91.bakingapp.R;
import com.gmail.tarekmabdallah91.bakingapp.models.RecipeEntry;


@Database(entities = {RecipeEntry.class} , version = 1 ,exportSchema = false)
public abstract class RecipeDatabase extends RoomDatabase{

    private static RecipeDatabase instance;
    private static final String DB_NAME = "recipesDb";
    private static final Object LOCK = new Object();
    private static final String TAG = RecipeDatabase.class.getSimpleName();

    public static RecipeDatabase getInstance(Context context){
        if (null == instance){
            synchronized (LOCK) {
                Log.d(TAG, context.getString(R.string.creating_new_db_instance_msg));
                instance = Room.databaseBuilder(context.getApplicationContext()
                        , RecipeDatabase.class
                        , RecipeDatabase.DB_NAME)
                        .build();
            }
        }
        Log.d(TAG, context.getString(R.string.getting_db_instance_msg));
        return instance;
    }

    public abstract RecipeDao recipeDao ();
}
