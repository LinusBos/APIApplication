package org.example;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Shoe {

    private String shoeName;
    private int size;
    private String shoeBrand;
    private String shoeGender;
    private String shoeStyle;
    private String shoePhoto;
    private int shoeId;

    public Shoe(int shoeId, String shoeName, String shoeBrand, String shoeGender, String shoeStyle, String shoePhoto) {
        this.shoeId = shoeId;
        this.shoeName = shoeName;
        this.shoeBrand = shoeBrand;
        this.shoeGender = shoeGender;
        this.shoeStyle = shoeStyle;
        this.shoePhoto = shoePhoto;
    }

}
