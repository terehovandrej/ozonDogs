package dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
@ToString
public class Items {
    public List<Item> getItems() {
        return items;
    }

    @SerializedName("items")
    private List<Item> items;
    @ToString
    @Getter
    public class Item {
        @SerializedName("name")
        private String name;
    }
}

