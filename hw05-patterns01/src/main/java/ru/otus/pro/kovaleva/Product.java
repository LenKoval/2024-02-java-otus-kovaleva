package ru.otus.pro.kovaleva;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public final class Product {
    private final int id;
    private final String title;
    private final String description;
    private final int cost;
    private final int weight;
    private final int length;
    private final int height;

    public static Builder builder() {
        return new Builder();
    }

    private Product(Builder builder) {
        id = builder.id;
        title = builder.title;
        description = builder.description;
        cost = builder.cost;
        weight = builder.weight;
        length = builder.length;
        height = builder.height;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Builder {
        private int id;
        private String title;
        private String description;
        private int cost;
        private int weight;
        private int length;
        private int height;

        public Product build(){
            return new Product(this);
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder cost(int cost) {
            this.cost = cost;
            return this;
        }

        public Builder weight(int weight) {
            this.weight = weight;
            return this;
        }

        public Builder length(int length) {
            this.length = length;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }
    }
}
