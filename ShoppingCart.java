/*
 		   Catalog
   ________________________
   | Product Name	|Price |
   |________________|______|
   | Product A		| $20  |
   | Product B		| $40  |
   | Product C		| $50  |
   |________________|______|

Discount Rules:

=> "flat_10_discount": If cart total exceeds $200, apply a flat $10 discount on the cart total.
=> "bulk_5_discount": If quantity of any single product exceeds 10 units, apply a 5% discount on that item's total price.
=> "bulk_10_discount": If total quantity exceeds 20 units, apply a 10% discount on the cart total.
=> "tiered_50_discount": If total quantity exceeds 30 units & any single product quantity greater than 15, then apply a 50% discount on products which are above  15 quantity.
    The first 15 quantity have the original price and unit above 15 will get 50% discount.
 
Note: Only one rule can be applied per purchase. If multiple discounts are applicable, the program calculates the discount amount for each rule and applies the most beneficial one for customer.

Fees:

=> Gift wrap fee: $1 per unit.
=> Shipping fee: 10 units can be packed in one package, and the shipping fee for each package is $5.

*/

import java.util.*;

public class ShoppingCart {
    public static void main(String[] args) {
        // Catalog
        String[] products = {"Product A", "Product B", "Product C"};
        double[] prices = {20.0, 40.0, 50.0};

        // Discount Rules
        double flat_10_Discount = 200.0;
        double flat_10_Discount_Amount = 10.0;
        int bulk_5_Discount_Quantity = 10;
        double bulk_5_Discount_Percentage = 0.05;
        int bulk_10_Discount_Quantity = 20;
        double bulk_10_Discount_Percentage = 0.10;
        int tiered_50_Discount_Quantity = 30;
        int tiered_50_Discount_ProductQuantity = 15;
        double tiered_50_Discount_Percentage = 0.50;

        // Fees
        double giftWrapFeePerUnit = 1.0;
        int shippingFeeUnitPerPackage = 10;
        double shippingFeePerPackage = 5.0;

        // Input
        Scanner sc = new Scanner(System.in);
        int[] quantities = new int[products.length];
        String[] giftWraps = new String[products.length];

        for (int i = 0; i < products.length; i++) {
            System.out.print("Enter the quantity of " + products[i] + ": ");
            quantities[i] = sc.nextInt();

            System.out.print("Is " + products[i] + " wrapped as a gift? (Yes/No): ");
            giftWraps[i] = sc.next();
        }
        
        //Gift Wrap Fee
        double giftWrapFee = 0.0;
        int totalQuantity = 0;
        for (int i = 0; i < products.length; i++) {
            totalQuantity += quantities[i];
            if (giftWraps[i].equals("Yes")) {
                giftWrapFee += quantities[i] * giftWrapFeePerUnit;
            }
        }
        
        // Calculation
        double subtotal = 0.0;
        double maxDiscount = 0.0;
        String appliedDiscount = "";
        double discountAmount = 0.0;
        double flatDiscount=0.0;
        double bulkDiscount=0.0;
        double tieredDiscount=0.0;

        for (int i = 0; i < products.length; i++) {
            double productTotal = prices[i] * quantities[i];
            subtotal += productTotal;

            if (quantities[i] > bulk_5_Discount_Quantity && totalQuantity <= tiered_50_Discount_Quantity) {
                bulkDiscount = productTotal * bulk_5_Discount_Percentage;
                if (bulkDiscount > maxDiscount) {
                    maxDiscount = bulkDiscount;
                    appliedDiscount = "bulk_5_discount";
                    discountAmount = bulkDiscount;
                }
            }

            if (totalQuantity > bulk_10_Discount_Quantity) {
                bulkDiscount = subtotal * bulk_10_Discount_Percentage;
                if (bulkDiscount > maxDiscount) {
                    maxDiscount = bulkDiscount;
                    appliedDiscount = "bulk_10_discount";
                    discountAmount = bulkDiscount;
                }
            }

            if (totalQuantity > tiered_50_Discount_Quantity && quantities[i] > tiered_50_Discount_ProductQuantity) {
                tieredDiscount += (quantities[i] - tiered_50_Discount_ProductQuantity) * prices[i] * tiered_50_Discount_Percentage;
            }
            
            if (tieredDiscount > maxDiscount) {
                maxDiscount = tieredDiscount;
                appliedDiscount = "tiered_50_discount";
                discountAmount = tieredDiscount;
            }
        }

        if (subtotal > flat_10_Discount) {
            flatDiscount = flat_10_Discount_Amount;
            if (flatDiscount > maxDiscount) {
                maxDiscount = flatDiscount;
                appliedDiscount = "flat_10_discount";
                discountAmount = flatDiscount;
            }
        }

        

        double shippingFee = Math.ceil((double) totalQuantity / shippingFeeUnitPerPackage) * shippingFeePerPackage;
        double total = subtotal - discountAmount + shippingFee + giftWrapFee;

        // Output
        System.out.println();
        System.out.println("Product Details:");
        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i] + " - Quantity: " + quantities[i] + ", Total: $" + (prices[i] * quantities[i]));
        }
        System.out.println("Subtotal: $" + subtotal);
        System.out.println("Discount Applied: " + appliedDiscount + ", Discount Amount: $" + discountAmount);
        System.out.println("Shipping Fee: $" + shippingFee);
        System.out.println("Gift Wrap Fee: $" + giftWrapFee);
        System.out.println("Total: $" + total);

        sc.close();
    }
}
