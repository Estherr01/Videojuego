package com.example.buscaminasentrega;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;


public class Panel extends View implements View.OnTouchListener{

    int numRows;
    int numColumns;
    int sizeText;
    int cordX,cordY;
    int width = 0;
    int height = 0;
    int row=0;
    int column=0;
    int[][] boxes;
    boolean[][] pressed;
    boolean[][] flagsArray;
    int numBombs;
    int points = 0; //si los points llegan a 64-numBombs -> has ganado
    int gameState = 0;

    public Panel(Context context, int numColumns, int numRows, int sizeText, int numBombs) {

        super(context);
        this.setOnTouchListener(this);

        this.numBombs = numBombs;
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.sizeText = sizeText;

        boxes = new int[numRows][numColumns];
        pressed = new boolean[numRows][numColumns];
        flagsArray = new boolean[numRows][numColumns];

        //------------------inicializamos el tablero a 0 y las boxes presseds en false
        for (int f = 0; f < numRows; f++) { //rows
            for (int c = 0; c < numColumns; c++) { //columns

                boxes[f][c] = 0;
                pressed[f][c] = false;
                System.out.println(pressed[f][c]);
                flagsArray[f][c] = false;
            }
        }

        //------------------colocamos las numBombs
        for (int i = 0; i < numBombs; i++) {

            int fr, cr; //row random, column random
            fr = (int)(Math.random()*numRows);
            cr = (int)(Math.random()*numColumns);

            if(boxes[fr][cr] != 10) {
                boxes[fr][cr] = 10;

            }else {

                i--; //lo volvemos a intentar
            }
        }

        //incrementamos el numero de cada casilla
        //si la casilla no es una bomba, miramos las numBombs que tiene alrededor
        for (int f = 0; f < numRows; f++) { //rows
            for (int c = 0; c < numColumns; c++) { //columns

                try{ if(boxes[f][c]!=10) if(boxes[f-1][c-1] == 10) boxes[f][c]++; }catch (Exception e){}
                try{ if(boxes[f][c]!=10) if(boxes[f][c-1] == 10) boxes[f][c]++; }catch (Exception e){}
                try{ if(boxes[f][c]!=10) if(boxes[f+1][c-1] == 10) boxes[f][c]++; }catch (Exception e){}
                try{ if(boxes[f][c]!=10) if(boxes[f-1][c] == 10) boxes[f][c]++; }catch (Exception e){}
                try{ if(boxes[f][c]!=10) if(boxes[f+1][c] == 10) boxes[f][c]++; }catch (Exception e){}
                try{ if(boxes[f][c]!=10) if(boxes[f-1][c+1] == 10) boxes[f][c]++; }catch (Exception e){}
                try{ if(boxes[f][c]!=10) if(boxes[f][c+1] == 10) boxes[f][c]++; }catch (Exception e){}
                try{ if(boxes[f][c]!=10) if(boxes[f+1][c+1] == 10) boxes[f][c]++; }catch (Exception e){}
            }
        }


    } //fin constructor

    protected void setGameState( int gameState){
        this.gameState = gameState;
        this.invalidate();
    }
    protected void onDraw(Canvas canvas){

        Paint pincel = new Paint();
        pincel.setStyle(Paint.Style.STROKE);

        width = canvas.getWidth(); //1050
        height = canvas.getHeight(); //1050
        int unidad = width/numColumns;

        //----------------------------------revisamos cada casilla para ver cuales podemos expandir y pintar
        for (int f = 0; f < numRows; f++) { //rows
                for (int c = 0; c < numColumns; c++) { //columns

                expandir(); //miramos cada casilla y expandimos

                if(pressed[f][c]){

                    Paint casilla = new Paint();
                    casilla.setStrokeWidth(2);
                    casilla.setARGB(255,20, 168, 118);//255, 192, 188, 181
                    int x = (c*unidad); //las columns se incrementan según se incrementa la x
                    int y = (f*unidad);
                    canvas.drawRect(x, y,x+unidad,y+unidad,casilla);

                    Paint texto = new Paint();

                    if(boxes[f][c] == 10){ //bomba

                        casilla.setARGB(200,150, 0, 0);
                        texto.setColor(Color.BLACK);
                        texto.setTextSize(sizeText);

                        canvas.drawRect(x, y,x+unidad,y+unidad,casilla);
                        canvas.drawText("X", x+(unidad/3), y+(unidad/2), texto);

                        gameState = 1;

                    }else if(boxes[f][c] == 0){

                        texto.setColor(Color.WHITE);
                        texto.setTextSize(sizeText);
                        canvas.drawText(" ", x+(unidad/3), y+(unidad/2), texto);

                    }else{ //no hay bomba ni se expande

                        if(boxes[f][c] == 1) texto.setARGB(255,54, 13, 183);
                        else if(boxes[f][c] == 2) texto.setARGB(255,0, 100, 0);
                        else if(boxes[f][c] == 3) texto.setARGB(255,92, 0, 41);
                        else texto.setColor(Color.MAGENTA);

                        texto.setTextSize(sizeText);
                        canvas.drawText(boxes[f][c]+"", x+(unidad/3), y+(unidad/2), texto);

                    }
                }else if(flagsArray[f][c]){ //si la casilla no ha sido destapada, es que hemos puesto una bandera

                    int x = (c*unidad); //las columns se incrementan según se incrementa la x
                    int y = (f*unidad);

                    //-------------------------------pintamos bandera
                    Paint bandera = new Paint();
                    bandera.setStyle(Paint.Style.FILL);

                    bandera.setColor(Color.RED);

                    canvas.drawRect(x+(unidad/4), y+(unidad/4),x+3*(unidad/4),y+5*(unidad/8),bandera);// D

                    bandera.setColor(Color.BLACK);

                    canvas.drawRect(x+(unidad/4), y+(unidad/4),x+3*(unidad/8),y+4*(unidad/4),bandera);// I


                }

            }
        }

        //----------------------------------pintamos las celdas por encima

        pincel.setColor(Color.BLACK);
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth(3);

        for(int i = 0; i < numRows; i++){ //verticales
            canvas.drawLine(unidad*i, 0, unidad*i, height, pincel); //linea
        }

        for(int i = 0; i < numColumns; i++){ //horizontales
            canvas.drawLine(0, unidad*i, width, unidad*i, pincel); //linea
        }

        //-------------------------------------------------------------------------Sumar points
        for (int f = 0; f < numRows; f++) { //rows
            for (int c = 0; c < numColumns; c++) { //columns
                if(pressed[f][c]) points++;
            }
        }

        if(points == ((numRows*numColumns)-numBombs)) gameState = 2; //GANASTE!
        else points = 0; //si tras la comprobacion no tienes todos los points, aún no has ganado

        //-------------------------Estado del juego: si es distinto de 0, has ganado o has perdido
        switch (gameState){
            default:
                break;
            case 1://GAME OVER

                //--Destapamos todas las numBombs
                for (int f = 0; f < numRows; f++) {
                    for (int c = 0; c < numColumns; c++) {
                        if(boxes[f][c] == 10) pressed[f][c] = true;
                    }
                }
                Paint paint = new Paint();
                paint.setARGB(255,216, 0, 0);
                paint.setTextSize(150);
                canvas.drawText("GAME OVER", 70, 500, paint);
                break;
            case 2: //--HAS GANADO
                Paint paint_2 = new Paint();
                paint_2.setARGB(170,0,0,0);
                paint_2.setTextSize(150);
                canvas.drawText("HAS GANADO", 70, 500, paint_2);
                break;

        }
    } //fin onDraw

    private void expandir() { //--------------------- expandir boxes

        //Revisamos todas las boxes por cada iteración
        for (int f = 0; f < numRows; f++) { //rows
            for (int c = 0; c < numColumns; c++) { //columns

                if(pressed[f][c] && boxes[f][c] == 0){

                        try{ pressed[f-1][c-1] = true;  }catch (Exception e){}
                        try{ pressed[f][c-1] = true;  }catch (Exception e){}
                        try{ pressed[f+1][c-1] = true;  }catch (Exception e){}
                        try{ pressed[f-1][c] = true;  }catch (Exception e){}
                        try{ pressed[f+1][c] = true;  }catch (Exception e){}
                        try{ pressed[f-1][c+1] = true;  }catch (Exception e){}
                        try{ pressed[f][c+1] = true;  }catch (Exception e){}
                        try{ pressed[f+1][c+1] = true;  }catch (Exception e){}
                }
            }
        }

    }

    int veces = 0; //variable para que el valor de la bandera cambie una vez cuando bajamos el dedo (case 1)
    // pero no vuelva a cambiar cuando lo levantamos (case 2)
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        //-----------------------------------Si estado de juego es distinto de 0 es que has perdido o has ganado
        if(gameState == 0){

            cordY = (int) event.getY();
            cordX = (int) event.getX();

            row = (cordY/(height/numRows)); //dividimos las coordenadas entre lo qe mide una casilla (width/8)
            column = (cordX/(width/numColumns));

            if(PlayActivity.flagsMode){ //si estamos en modo flagsArray, no podemos pulsar boxes, solo pintar flagsArray

                if(!pressed[row][column]){ //solo si la casilla no está pressed podemos poder una bandera

                    veces++;
                    switch (veces){
                        case 1:
                            flagsArray[row][column] = !flagsArray[row][column];
                            break;
                        case 2:
                            veces = 0;
                            break;
                    }

                }

            }else{ //modo normal (destapar boxes)

                if(!pressed[row][column] && !flagsArray[row][column]){ //solo destapamos si no estaba pressed y no habia una bandera

                    pressed[row][column] = true;
                }


            }

            //-----------destapamos alrededor de la casilla pressed si contiene un número que no es 0 y si el número de la casilla coincide con el numero de flagsArray alrededor
            if(boxes[row][column] != 0 && pressed[row][column]){

                //----comprobamos las 8 boxes de alrededor en busca de flagsArray
                int flagFound = 0;
                try{ if(flagsArray[row-1][column-1]) flagFound++;  }catch (Exception e){}
                try{ if(flagsArray[row][column-1]) flagFound++;  }catch (Exception e){}
                try{ if(flagsArray[row+1][column-1]) flagFound++;  }catch (Exception e){}
                try{ if(flagsArray[row-1][column]) flagFound++;  }catch (Exception e){}
                try{ if(flagsArray[row+1][column]) flagFound++;  }catch (Exception e){}
                try{ if(flagsArray[row-1][column+1]) flagFound++;  }catch (Exception e){}
                try{ if(flagsArray[row][column+1]) flagFound++;  }catch (Exception e){}
                try{ if(flagsArray[row+1][column+1]) flagFound++;  }catch (Exception e){}

                //----rellenamos las 8 boxes de alrededor que no sean flagsArray y que no estén ya presseds
                if(flagFound == boxes[row][column]){
                    try{ if(!flagsArray[row-1][column-1] && !pressed[row-1][column-1]) pressed[row-1][column-1]=true;  }catch (Exception e){}
                    try{ if(!flagsArray[row][column-1] && !pressed[row][column-1]) pressed[row][column-1]=true; }catch (Exception e){}
                    try{ if(!flagsArray[row+1][column-1] && !pressed[row+1][column-1]) pressed[row+1][column-1]=true;  }catch (Exception e){}
                    try{ if(!flagsArray[row-1][column] && !pressed[row-1][column]) pressed[row-1][column]=true;  }catch (Exception e){}
                    try{ if(!flagsArray[row+1][column] && !pressed[row+1][column]) pressed[row+1][column]=true;  }catch (Exception e){}
                    try{ if(!flagsArray[row-1][column+1] && !pressed[row-1][column+1]) pressed[row-1][column+1]=true;  }catch (Exception e){}
                    try{ if(!flagsArray[row][column+1] && !pressed[row][column+1]) pressed[row][column+1]=true;;  }catch (Exception e){}
                    try{ if(!flagsArray[row+1][column+1] && !pressed[row+1][column+1]) pressed[row+1][column+1]=true;  }catch (Exception e){}
                }
            }//fin if

        }//fin estado = 0


        invalidate();
        return true;
    }

}