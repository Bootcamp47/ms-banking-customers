package com.bc47.msbankingcustomers.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
public class Customer {

    @Id
    private String id;
    private String customerType;
    private String name;
    private String docType;
    private String docNumber;
    private String createdAt;
    private String address;
    private String phoneNumber;
    private String status;
    private String email;
    private String mobilePhoneImeiNumber;
    private Integer ownedPasiveProductsQty;
    private Integer ownedActiveProductsQty;
}
