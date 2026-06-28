package com.github.mixinmatch.appointments.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;
@Entity
@Table(name = "CATALOG", schema = "catalog")
public class Item {

        @Id
        private UUID id;
        /***
         * id uuid PRIMARY KEY,
         * name varchar(256),
         * merchant varchar(256),
         * merchantId uuid,
         * photo varchar(256),
         * isLiquidationSale boolean DEFAULT FALSE
         */
        private String name;
        private String merchant;
        private UUID merchantId;

        @Column(name = "photo")
        private String photoLink;
        private boolean isLiquidationSale;


        public Item() {
        }

        public UUID getId() {
                return id;
        }

        public void setId(UUID id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getMerchant() {
                return merchant;
        }

        public void setMerchant(String merchant) {
                this.merchant = merchant;
        }

        public UUID getMerchantId() {
                return merchantId;
        }

        public void setMerchantId(UUID merchantId) {
                this.merchantId = merchantId;
        }

        public String getPhotoLink() {
                return photoLink;
        }

        public void setPhotoLink(String photoLink) {
                this.photoLink = photoLink;
        }

        public boolean isLiquidationSale() {
                return isLiquidationSale;
        }

        public void setLiquidationSale(boolean liquidationSale) {
                isLiquidationSale = liquidationSale;
        }
}
