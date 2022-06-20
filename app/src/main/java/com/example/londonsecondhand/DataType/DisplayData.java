package com.example.londonsecondhand.DataType;

public class DisplayData {
    public static class ListItem{
        private Integer imageId;
        private String title;
        private String description;
        private double price;

        public Integer getImageId(){
            return  imageId;
        }

        public String getTitle(){
            return title;
        }

        public String getDescription(){
            return  description;
        }

        public  double getPrice(){
            return  price;
        }

        public ListItem(Integer imageId, String title, String description, double price){
            this.imageId = imageId;
            this.title = title;
            this.description = description;
            this.price = price;
        }
    }
}
