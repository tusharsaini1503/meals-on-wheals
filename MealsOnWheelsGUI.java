import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MealsOnWheelsGUI extends JFrame implements ActionListener {

    private final String[] menuItems = {
            "Burger", "Sandwich", "French Fries", "Chowmein", "Maggi", "Pasta", "Pao Bhaji", "Pizza",
            "Dosa", "Momos", "Fried Rice", "Kebab", "Egg Roll", "Chicken Roll", "Manchurian",
            "Masala Tea", "Tea", "Cold Coffee", "Hot Coffee", "Cold Drink", "Quit"
    };

    private final int[] itemPrices = {
            69, 49, 35, 49, 40, 79, 99, 149, 79, 50, 59, 50, 50, 80, 60, 30, 20, 40, 35, 60, 0
    };

    private final JLabel[] itemLabels;
    private final JTextField[] itemQuantities;
    private final JButton orderButton;
    private final JTextArea orderSummary;
    private final Random random = new Random();
    private final int orderNumber = random.nextInt(1000);

    public MealsOnWheelsGUI() {
        setTitle("Meals On Wheels");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(menuItems.length + 3, 2));

        itemLabels = new JLabel[menuItems.length];
        itemQuantities = new JTextField[menuItems.length];

        for (int i = 0; i < menuItems.length; i++) {
            itemLabels[i] = new JLabel(menuItems[i]);
            itemQuantities[i] = new JTextField("0", 3);
            add(itemLabels[i]);
            add(itemQuantities[i]);
        }

        orderButton = new JButton("Place Order");
        orderButton.addActionListener(this);
        add(orderButton);

        orderSummary = new JTextArea(10, 20);
        orderSummary.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderSummary);
        add(scrollPane);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new MealsOnWheelsGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int sum = 0;
        StringBuilder orderDetails = new StringBuilder("Order Number: " + orderNumber + "\n\nYour Orders are:\n\n");

        for (int i = 0; i < menuItems.length; i++) {
            int quantity = Integer.parseInt(itemQuantities[i].getText());
            sum += quantity * itemPrices[i];
            if (quantity > 0) {
                orderDetails.append(menuItems[i]).append(" * ").append(quantity).append(" = ").append(quantity * itemPrices[i]).append("\n");
            }
        }

        double gstAmount = sum * 0.18; // GST calculation (18%)
        double totalAmount = sum + gstAmount; // Total amount including GST

        orderSummary.setText(orderDetails + "\nYour amount for your ordered items is = Rs." + sum + "\n" +
                "GST (18%): Rs." + gstAmount + "\nTotal amount including GST: Rs." + totalAmount + "\n\nThank you for your order");
    }
}
