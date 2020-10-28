package com.covid19.model;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class Structure {


    @Expose(serialize = true, deserialize = true)
    private Integer id;

    @Expose(serialize = true, deserialize = true)
    private String name;

    @Expose(serialize = true, deserialize = true)
    private String address;

    @Expose(serialize = true, deserialize = true)
    private String imageLink;

    @Expose(serialize = true, deserialize = true)
    private String site;

    @Expose(serialize = true, deserialize = true)
    private String email;

    @Expose(serialize = true, deserialize = true)
    private String state;

    @Expose(serialize = true, deserialize = true)
    private String city;

    @Expose(serialize = true, deserialize = true)
    private String phone;

    @Expose(serialize = true, deserialize = true)
    private Type type;

    @Expose(serialize = true, deserialize = true)
    private Double priceMin;

    @Expose(serialize = true, deserialize = true)
    private Double priceMax;

    @Expose(serialize = true, deserialize = true)
    private BigDecimal latitude;

    @Expose(serialize = true, deserialize = true)
    private BigDecimal longitude;

    @Expose(serialize = true, deserialize = true)
    private Date closingHours;

    @Expose(serialize = true, deserialize = true)
    private Date openingHours;


    @Expose(serialize = false, deserialize = false)
    private Double averageRating;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Structure structure = (Structure) o;

        if (name != null ? !name.equals(structure.name) : structure.name != null) return false;
        if (latitude != null ? !latitude.equals(structure.latitude) : structure.latitude != null) return false;
        return longitude != null ? longitude.equals(structure.longitude) : structure.longitude == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }
}
