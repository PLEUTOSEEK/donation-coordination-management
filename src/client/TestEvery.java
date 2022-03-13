/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import com.github.javafaker.Faker;
import entity.Campaign;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class TestEvery {

    public static void main(String[] args) {
        Faker faker = new Faker();
        Campaign campaign = new Campaign();
        campaign.generateDummyCampaign();//faker.expression("/^(\\+?6?01)[0|1|2|3|4|6|7|8|9]\\-*[0-9]{7,8}$/")

        for (int i = 0; i < 10; i++) {
            System.out.println(faker.random().nextInt(0, 1));
        }

    }

}
