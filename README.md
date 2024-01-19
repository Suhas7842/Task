# Shopping Cart


### Catalog: ###
       
| Product Name  |     Price     |
|--------------:|:-------------:| 
| Product A     |      $20      |
| Product B		  |      $40      |
| Product C		  |      $50      |
---------------------------------

### Discount Rules: ###

1. "flat_10_discount": If cart total exceeds $200, apply a flat $10 discount on the cart total.

2. "bulk_5_discount": If quantity of any single product exceeds 10 units, apply a 5% discount on that item's total price.

3. "bulk_10_discount": If total quantity exceeds 20 units, apply a 10% discount on the cart total.

4. "tiered_50_discount": If total quantity exceeds 30 units & any single product quantity greater than 15, then apply a 50% discount on products which are above  15 quantity.The first 15 quantity have the original price and unit above 15 will get 50% discount.
 
#### Note: ####
Only one rule can be applied per purchase. If multiple discounts are applicable, the program calculates the discount amount for each rule and applies the most beneficial one for the customer.

---------------------------------

### Fees: ###

1. Gift wrap fee: $1 per unit.

2. Shipping fee: 10 units can be packed in one package, and the shipping fee for each package is $5.

---------------------------------

#### This Java program calculates the total cost of a shopping cart, considering various discounts, fees, and gift wrapping.

---------------------------------

## Usage
1. Compile the program:

   ```
   javac ShoppingCart.java

2. Run the program:
   ```
   java ShoppingCart

---------------------------------

## Sample Usage
### Example Input:
1. Product A: Quantity - 2, Gift Wrap - No
2. Product B: Quantity - 3, Gift Wrap - Yes
3. Product C: Quantity - 1, Gift Wrap - No

### Example Output:
- Subtotal: $XXX.XX
- Discounts Applied: bulk_5_discount, Discount Amount: $X.XX
- Shipping Fee: $X.XX
- Gift Wrap Fee: $X.XX
- Total: $XXX.XX
