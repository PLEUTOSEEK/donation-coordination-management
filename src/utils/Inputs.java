/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.time.LocalDate;

/**
 *
 * @author Tee Zhuo Xuan
 */
interface Inputs {

    public LocalDate askDate();

    public String askStr();

    public int askInt();

    public double askDouble();
}
