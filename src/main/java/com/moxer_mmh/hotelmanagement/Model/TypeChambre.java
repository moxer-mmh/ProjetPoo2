package com.moxer_mmh.hotelmanagement.Model;

public enum TypeChambre {
    SIMPLE, DOUBLE, SUITE;

    public static void displayTypeChabmre() {
        TypeChambre[] values = TypeChambre.values();
        for (TypeChambre value : values) {
            System.out.println(value);
        }
    }
}
