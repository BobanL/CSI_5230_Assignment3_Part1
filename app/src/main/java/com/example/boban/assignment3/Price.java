package com.example.boban.assignment3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Price extends ConstraintLayout {
    TextView priceText = null;
    Button plusButton, minusButton, buttonX1, buttonX5, buttonX10, buttonX100 = null;
    private String priceValue;
    private int modifier = 1;
    private int price = 0;
    private int priceColor = Color.RED;
    private int priceHeight = 0;
    private View.OnClickListener modifierOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            modifier = (int) v.getTag();
        }
    };

    public Price(Context context) {
        super(context);
        init(null, 0);
    }

    public Price(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);

        // Inflate the view
        LayoutInflater inflater = LayoutInflater.from(context);
        ConstraintLayout container = (ConstraintLayout) inflater.inflate(R.layout.sample_price, this);

        priceText = container.findViewById(R.id.textAmount);
        price = Integer.parseInt(getPriceValue());
        priceText.setText(getPriceValue());
        plusButton = container.findViewById(R.id.buttonPlus);
        minusButton = container.findViewById(R.id.buttonMinus);
        buttonX1 = container.findViewById(R.id.buttonX1);
        buttonX5 = container.findViewById(R.id.buttonX5);
        buttonX10 = container.findViewById(R.id.buttonX10);
        buttonX100 = container.findViewById(R.id.buttonX100);

        buttonX1.setTag(1);
        buttonX5.setTag(5);
        buttonX10.setTag(10);
        buttonX100.setTag(100);

        buttonX1.setOnClickListener(modifierOnClickListener);
        buttonX5.setOnClickListener(modifierOnClickListener);
        buttonX10.setOnClickListener(modifierOnClickListener);
        buttonX100.setOnClickListener(modifierOnClickListener);

        plusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                price = Integer.parseInt(getPriceValue());
                String priceNew = Integer.toString(price + modifier);
                priceText.setText(priceNew);
                setPriceValue(priceNew);
            }
        });
        minusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                price = Integer.parseInt(getPriceValue());
                String priceNew = Integer.toString(price - modifier);
                priceText.setText(priceNew);
                setPriceValue(priceNew);
            }
        });
    }

    public Price(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(String priceValue) {
        this.priceValue = priceValue;
    }

    public int getPriceColor() {
        return priceColor;
    }

    public void setPriceColor(int priceColor) {
        this.priceColor = priceColor;
    }

    public int getPriceHeight() {
        return priceHeight;
    }

    public void setPriceHeight(int priceHeight) {
        this.priceHeight = priceHeight;
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.Price, defStyle, 0);

        priceValue = a.getString(R.styleable.Price_priceValue);
        priceColor = a.getColor(R.styleable.Price_priceColor, Color.BLUE);
        priceHeight = a.getInt(R.styleable.Price_priceHeight, 64);

        a.recycle();
    }
}
