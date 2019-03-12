package com.teamtreehouse.colorizer;

import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    int[] imageResIds = {R.drawable.cuba1, R.drawable.cuba2, R.drawable.cuba3};
    int imageIndex = 0;
    boolean color = true;
    boolean red = true;
    boolean green = true;
    boolean blue = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
        loadImage();
    }

    private void loadImage() {
        Glide.with(this).load(imageResIds[imageIndex]).into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options_menu, menu); //enlaza el layout options_menu para mostrar

        Drawable nextImageDrawable = menu.findItem(R.id.nextImage).getIcon();
        nextImageDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP
        );

        /*MenuItem menuItem = menu.add("Next Image"); // se añade una opcion en el menu
        //menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS); // coloca fija la opcion del menu como boton
        menuItem.setIcon(R.drawable.ic_add_a_photo_black_24dp); // se enlazo el icono para usar y mostrar en el boton de menu
        menuItem.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP); //cambia el color del icono que se agrego anteriormente */

        return true;
    }

    private void updateSaturation() {
        ColorMatrix colorMatrix = new ColorMatrix();
        if (color) {
            red = green = blue = true;
            colorMatrix.setSaturation(1);
        } else {
            colorMatrix.setSaturation(0);
        }
        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
        imageView.setColorFilter(colorFilter);
    }

    private void updateColors() {
        ColorMatrix colorMatrix = new ColorMatrix();
        float[] matrix = {
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0,
        };
        if (!red) matrix[0] = 0;
        if (!green) matrix[6] = 0;
        if (!blue) matrix[12] = 0;
        colorMatrix.set(matrix);
        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
        imageView.setColorFilter(colorFilter);
    }
}
