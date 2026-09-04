package fooddelivery;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {

    // =========================================================
    // COLORS
    // =========================================================

    private final Color darkBlue = new Color(31, 41, 55);
    private final Color blue = new Color(37, 99, 235);
    private final Color green = new Color(13, 148, 136);
    private final Color purple = new Color(124, 58, 237);
    private final Color orange = new Color(230, 76, 60);
    private final Color lightBackground = new Color(248, 246, 241);

    // =========================================================
    // DATA
    // =========================================================

    static List<Customer> customers = new ArrayList<>();
    static List<Restaurant> restaurants = new ArrayList<>();
    static List<Food> foods = new ArrayList<>();
    static List<Order> orders = new ArrayList<>();
    static List<DeliveryPartner> deliveryPartners = new ArrayList<>();

    static int customerId = 1;
    static int restaurantId = 1;
    static int foodId = 1;
    static int orderId = 100;
    static int deliveryPartnerId = 1;

    // =========================================================
    // MEALIX LOGO
    // =========================================================

    private BufferedImage createMealixLogo() {
        // Logo based on the image supplied by the user.
        // It is embedded directly in the Java source, so no internet
        // connection or separate image file is required.
        String logoBase64 =
                "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";

        try {
            byte[] imageBytes = java.util.Base64.getDecoder().decode(logoBase64);
            java.io.ByteArrayInputStream input =
                    new java.io.ByteArrayInputStream(imageBytes);
            BufferedImage original = javax.imageio.ImageIO.read(input);

            BufferedImage logo = new BufferedImage(
                    70, 70, BufferedImage.TYPE_INT_ARGB
            );

            Graphics2D g2 = logo.createGraphics();
            g2.setRenderingHint(
                    RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC
            );
            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            int w = original.getWidth();
            int h = original.getHeight();
            double scale = Math.min(70.0 / w, 70.0 / h);
            int newW = (int) Math.round(w * scale);
            int newH = (int) Math.round(h * scale);
            int x = (70 - newW) / 2;
            int y = (70 - newH) / 2;

            g2.drawImage(original, x, y, newW, newH, null);
            g2.dispose();

            return logo;
        } catch (Exception e) {
            // Fallback if the embedded logo cannot be decoded.
            BufferedImage fallback = new BufferedImage(
                    70, 70, BufferedImage.TYPE_INT_ARGB
            );
            Graphics2D g = fallback.createGraphics();
            g.setColor(new Color(31, 41, 55));
            g.fillOval(8, 8, 54, 54);
            g.setColor(new Color(245, 158, 11));
            g.fillOval(20, 20, 30, 30);
            g.dispose();
            return fallback;
        }
    }

    // =========================================================
    // LOGIN
    // =========================================================

    private String loggedInRole = "GUEST";

    // ID of the customer currently logged in
    private int currentCustomerId = -1;

    // ID of the delivery partner currently logged in
    private int currentDeliveryPartnerId = -1;

    // =========================================================
    // CART
    // =========================================================

    private final List<CartItem> cart = new ArrayList<>();

    // =========================================================
    // GUI
    // =========================================================

    private JPanel contentPanel;
    private JLabel loginLabel;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public Main() {

        setTitle("Mealix - Food Delivery Management System");

        setSize(1400, 850);

        setMinimumSize(new Dimension(1100, 700));

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        loadSampleData();

        createUI();
    }

    // =========================================================
    // SAMPLE DATA
    // =========================================================

    private void loadSampleData() {

        if (!restaurants.isEmpty()) {
            return;
        }

        restaurants.add(
                new Restaurant(
                        restaurantId++,
                        "Food Palace",
                        "Bangalore"
                )
        );

        restaurants.add(
                new Restaurant(
                        restaurantId++,
                        "Spice Hub",
                        "Bangalore"
                )
        );

        customers.add(
                new Customer(
                        customerId++,
                        "Demo Customer",
                        "Bangalore",
                        "demo",
                        "demo123"
                )
        );

        foods.add(
                new Food(
                        foodId++,
                        "Chicken Biryani",
                        180,
                        1
                )
        );

        foods.add(
                new Food(
                        foodId++,
                        "Veg Biryani",
                        140,
                        1
                )
        );

        foods.add(
                new Food(
                        foodId++,
                        "Pizza",
                        250,
                        2
                )
        );

        foods.add(
                new Food(
                        foodId++,
                        "Burger",
                        150,
                        2
                )
        );

        deliveryPartners.add(
                new DeliveryPartner(
                        deliveryPartnerId++,
                        "Rahul Kumar",
                        "Bangalore",
                        "rahul",
                        "rahul123"
                )
        );

        deliveryPartners.add(
                new DeliveryPartner(
                        deliveryPartnerId++,
                        "Arjun Singh",
                        "Bangalore",
                        "arjun",
                        "arjun123"
                )
        );
    }

    // =========================================================
    // CREATE UI
    // =========================================================

    private void createUI() {

        JPanel header = new JPanel(new BorderLayout());

        header.setBackground(new Color(15, 23, 42));

        header.setPreferredSize(
                new Dimension(1400, 90)
        );

        header.setBorder(
                new EmptyBorder(
                        10,
                        25,
                        10,
                        25
                )
        );

        // -----------------------------------------------------
        // LOGO + TITLE
        // -----------------------------------------------------

        JPanel brandPanel = new JPanel();
        brandPanel.setLayout(
                new FlowLayout(
                        FlowLayout.LEFT,
                        10,
                        5
                )
        );
        brandPanel.setBackground(new Color(15, 23, 42));

        JLabel logoLabel = new JLabel();

        // Built-in Mealix food logo.
        // It is drawn directly in Java, so it always appears
        // without requiring an internet connection or image file.
        logoLabel.setIcon(
                new ImageIcon(createMealixLogo())
        );

        brandPanel.add(logoLabel);

        // TITLE
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );
        titlePanel.setBackground(new Color(15, 23, 42));

        JLabel title = new JLabel("Mealix");
        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel(
                "FOOD DELIVERY MANAGEMENT SYSTEM"
        );
        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );
        subtitle.setForeground(orange);

        titlePanel.add(title);
        titlePanel.add(subtitle);

        brandPanel.add(titlePanel);

        header.add(
                brandPanel,
                BorderLayout.WEST
        );

        // LOGIN

        loginLabel = new JLabel("Login");

        loginLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        loginLabel.setForeground(darkBlue);

        loginLabel.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        loginLabel.setBorder(
                new EmptyBorder(
                        10,
                        20,
                        10,
                        20
                )
        );

        loginLabel.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e) {

                        loginLogout();
                    }
                }
        );

        header.add(
                loginLabel,
                BorderLayout.EAST
        );

        header.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(
                                0,
                                0,
                                1,
                                0,
                                new Color(226, 232, 240)
                        ),
                        new EmptyBorder(
                                10,
                                25,
                                10,
                                25
                        )
                )
        );

        add(
                header,
                BorderLayout.NORTH
        );

        // CONTENT

        contentPanel =
                new JPanel(
                        new BorderLayout()
                );

        add(
                contentPanel,
                BorderLayout.CENTER
        );

        showMainMenu();
    }

    // =========================================================
    // MAIN MENU
    // =========================================================

    private void showMainMenu() {

        contentPanel.removeAll();

        // Clean, professional background - no decorative circles.
        JPanel background = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(new Color(17, 24, 39));
                g2.fillRect(0, 0, getWidth(), getHeight());

                // Very subtle horizontal divider for a structured layout.
                g2.setColor(new Color(51, 65, 85));
                g2.fillRect(0, 0, getWidth(), 1);

                g2.dispose();
            }
        };

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JLabel heading = new JLabel("MAIN MENU");
        heading.setFont(new Font("Arial", Font.BOLD, 42));
        heading.setForeground(Color.WHITE);
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subHeading = new JLabel("Choose an account to continue");
        subHeading.setFont(new Font("Arial", Font.PLAIN, 14));
        subHeading.setForeground(new Color(203, 213, 225));
        subHeading.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel line = new JPanel();
        line.setBackground(new Color(37, 99, 235));
        line.setPreferredSize(new Dimension(70, 4));
        line.setMaximumSize(new Dimension(70, 4));
        line.setMinimumSize(new Dimension(70, 4));
        line.setAlignmentX(Component.CENTER_ALIGNMENT);

        center.add(heading);
        center.add(Box.createVerticalStrut(6));
        center.add(subHeading);
        center.add(Box.createVerticalStrut(12));
        center.add(line);
        center.add(Box.createVerticalStrut(24));

        JPanel loginGrid = new JPanel(new GridLayout(2, 2, 16, 16));
        loginGrid.setOpaque(false);
        loginGrid.setPreferredSize(new Dimension(900, 220));
        loginGrid.setMaximumSize(new Dimension(900, 220));
        loginGrid.setMinimumSize(new Dimension(700, 200));

        JPanel adminCard = createLoginCard(
                "Admin Login",
                "admin",
                blue
        );
        adminCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                adminLogin();
            }
        });

        JPanel customerCard = createLoginCard(
                "Customer Login",
                "customer",
                green
        );
        customerCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                customerLogin();
            }
        });

        JPanel restaurantCard = createLoginCard(
                "Restaurant Login",
                "restaurant",
                purple
        );
        restaurantCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                restaurantLogin();
            }
        });

        JPanel deliveryCard = createLoginCard(
                "Delivery Partner Login",
                "delivery",
                green
        );
        deliveryCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                deliveryPartnerLogin();
            }
        });

        loginGrid.add(adminCard);
        loginGrid.add(customerCard);
        loginGrid.add(restaurantCard);
        loginGrid.add(deliveryCard);

        center.add(loginGrid);
        center.add(Box.createVerticalStrut(18));

        JLabel footer = new JLabel("Secure • Simple • Food Delivery Management");
        footer.setFont(new Font("Arial", Font.PLAIN, 12));
        footer.setForeground(new Color(148, 163, 184));
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(footer);

        background.add(center);

        contentPanel.add(background, BorderLayout.CENTER);
        refresh();
    }

    // =========================================================
    // LOGIN CARD - COMPACT PROFESSIONAL LAYOUT
    // =========================================================

    private JPanel createLoginCard(
            String text,
            String type,
            Color cardColor) {

        JPanel card = new JPanel() {

            private boolean hover = false;

            {
                setOpaque(false);
                setCursor(new Cursor(Cursor.HAND_CURSOR));

                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        hover = true;
                        repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        hover = false;
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                int w = getWidth();
                int h = getHeight();
                int radius = 14;

                // Thin, controlled shadow - not decorative.
                g2.setColor(new Color(15, 23, 42, 18));
                g2.fillRoundRect(2, 3, w - 4, h - 3, radius, radius);

                // Main surface.
                g2.setColor(hover ? new Color(255, 255, 255) : Color.WHITE);
                g2.fillRoundRect(0, 0, w - 2, h - 3, radius, radius);

                // Strong but thin accent strip gives each role its identity.
                g2.setColor(cardColor);
                g2.fillRoundRect(0, 0, 7, h - 3, radius, radius);
                g2.fillRect(4, 0, 4, h - 3);

                // Professional border.
                g2.setColor(
                        hover
                                ? cardColor
                                : new Color(226, 232, 240)
                );
                g2.setStroke(new BasicStroke(hover ? 1.5f : 1f));
                g2.drawRoundRect(0, 0, w - 2, h - 3, radius, radius);

                // Icon block.
                int iconX = 24;
                int iconY = 19;
                int iconSize = 54;

                g2.setColor(new Color(
                        cardColor.getRed(),
                        cardColor.getGreen(),
                        cardColor.getBlue(),
                        18
                ));
                g2.fillRoundRect(iconX, iconY, iconSize, iconSize, 12, 12);

                g2.setColor(cardColor);
                g2.setFont(new Font("Segoe UI Symbol", Font.BOLD, 25));
                String icon = getLoginIcon(type);
                FontMetrics iconMetrics = g2.getFontMetrics();
                int iconWidth = iconMetrics.stringWidth(icon);
                int iconAscent = iconMetrics.getAscent();

                g2.drawString(
                        icon,
                        iconX + (iconSize - iconWidth) / 2,
                        iconY + (iconSize + iconAscent) / 2 - 4
                );

                // Main label.
                g2.setColor(darkBlue);
                g2.setFont(new Font("Arial", Font.BOLD, 17));
                g2.drawString(text, 96, 35);

                // Useful role description instead of decorative text.
                String description;
                if (type.equals("admin")) {
                    description = "System administration";
                } else if (type.equals("customer")) {
                    description = "Browse food and place orders";
                } else if (type.equals("restaurant")) {
                    description = "Manage menu and restaurant orders";
                } else {
                    description = "Manage assigned food deliveries";
                }

                g2.setColor(new Color(100, 116, 139));
                g2.setFont(new Font("Arial", Font.PLAIN, 12));
                g2.drawString(description, 96, 56);

                // Arrow / action indicator.
                g2.setColor(hover ? cardColor : new Color(148, 163, 184));
                g2.setFont(new Font("Arial", Font.BOLD, 26));
                g2.drawString("›", w - 30, 43);

                g2.dispose();
            }
        };

        // New compact thickness: about 88 px per card.
        card.setPreferredSize(new Dimension(440, 88));
        card.setMinimumSize(new Dimension(320, 80));
        card.setMaximumSize(new Dimension(440, 88));

        return card;
    }

    // =========================================================
    // LOGIN ICON
    // =========================================================

    private String getLoginIcon(String type) {

        if (type.equals("admin")) {
            return "⚙";
        }

        if (type.equals("customer")) {
            return "♙";
        }

        if (type.equals("delivery")) {
            return "▰";
        }

        return "▣";
    }

    // =========================================================
    // ADMIN LOGIN
    // =========================================================

    private void adminLogin() {

        if (
                showLoginDialog(
                        "Admin",
                        "admin",
                        "admin123"
                )
        ) {

            loggedInRole = "ADMIN";

            updateLoginLabel();

            showAdminOperations();
        }
    }

    // =========================================================
    // CUSTOMER LOGIN
    // =========================================================

    private void customerLogin() {

        String[] options = {"Login", "Register", "Cancel"};

        int choice = JOptionPane.showOptionDialog(
                this,
                "Customer Account",
                "Customer",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == 1) {
            registerCustomer();
            return;
        }

        if (choice != 0) {
            return;
        }

        Customer customer = authenticateCustomer();

        if (customer != null) {

            loggedInRole = "CUSTOMER";
            currentCustomerId = customer.id;

            updateLoginLabel();
            showCustomerHome();
        }
    }

    // =========================================================
    // RESTAURANT LOGIN
    // =========================================================

    private void restaurantLogin() {

        if (
                showLoginDialog(
                        "Restaurant",
                        "restaurant",
                        "restaurant123"
                )
        ) {

            loggedInRole = "RESTAURANT";

            updateLoginLabel();

            showRestaurantHome();
        }
    }

    // =========================================================
    // DELIVERY PARTNER LOGIN
    // =========================================================

    private void deliveryPartnerLogin() {

        DeliveryPartner partner = authenticateDeliveryPartner();

        if (partner != null) {

            loggedInRole = "DELIVERY PARTNER";

            currentDeliveryPartnerId = partner.id;

            updateLoginLabel();

            showDeliveryPartnerHome();
        }
    }

    private DeliveryPartner authenticateDeliveryPartner() {

        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();

        Object[] fields = {
                "Username:", username,
                "Password:", password
        };

        int result = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Delivery Partner Login",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return null;
        }

        String enteredUsername = username.getText().trim();
        String enteredPassword = new String(password.getPassword());

        for (DeliveryPartner partner : deliveryPartners) {
            if (partner.username.equalsIgnoreCase(enteredUsername)
                    && partner.password.equals(enteredPassword)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Delivery Partner login successful!"
                );

                return partner;
            }
        }

        JOptionPane.showMessageDialog(
                this,
                "Invalid username or password.",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE
        );

        return null;
    }

    // =========================================================
    // LOGIN DIALOG
    // =========================================================

    private boolean showLoginDialog(
            String role,
            String correctUsername,
            String correctPassword) {

        JTextField username =
                new JTextField();

        JPasswordField password =
                new JPasswordField();

        username.setPreferredSize(
                new Dimension(
                        250,
                        32
                )
        );

        password.setPreferredSize(
                new Dimension(
                        250,
                        32
                )
        );

        Object[] fields = {

                "Username:",
                username,

                "Password:",
                password
        };

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        fields,
                        role + " Login",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        if (
                result !=
                        JOptionPane.OK_OPTION
        ) {

            return false;
        }

        String enteredUsername =
                username.getText().trim();

        String enteredPassword =
                new String(
                        password.getPassword()
                );

        if (
                enteredUsername.equals(
                        correctUsername
                )
                        &&
                        enteredPassword.equals(
                                correctPassword
                        )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    role +
                            " login successful!"
            );

            return true;
        }

        JOptionPane.showMessageDialog(
                this,
                "Invalid username or password.",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE
        );

        return false;
    }

    // =========================================================
    // UPDATE LOGIN LABEL
    // =========================================================

    private void updateLoginLabel() {

        if (
                loggedInRole.equals(
                        "GUEST"
                )
        ) {

            loginLabel.setText(
                    "Login"
            );

        } else {

            loginLabel.setText(
                    loggedInRole +
                            " (Logged In)"
            );
        }
    }

    // =========================================================
    // LOGOUT
    // =========================================================

    private void loginLogout() {

        if (
                loggedInRole.equals(
                        "GUEST"
                )
        ) {

            showMainMenu();

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Do you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            loggedInRole = "GUEST";
            currentCustomerId = -1;
            currentDeliveryPartnerId = -1;

            cart.clear();

            updateLoginLabel();

            showMainMenu();
        }
    }

    // =========================================================
    // ADMIN OPERATIONS
    // =========================================================

    void showAdminOperations() {

        if (!loggedInRole.equals("ADMIN")) {
            showMainMenu();
            return;
        }

        contentPanel.removeAll();

        JPanel root = createDashboardRoot();
        root.add(createDashboardSidebar("ADMIN"), BorderLayout.WEST);

        JPanel main = createDashboardMain(
                "Admin Dashboard",
                "Manage customers, restaurants, food and orders"
        );

        // Summary cards
        JPanel summary = new JPanel(
                new GridLayout(1, 4, 14, 14)
        );
        summary.setBackground(lightBackground);

        summary.add(createSummaryCard(
                "Customers", customers.size(), "♙"
        ));
        summary.add(createSummaryCard(
                "Restaurants", restaurants.size(), "▣"
        ));
        summary.add(createSummaryCard(
                "Food Items", foods.size(), "☷"
        ));
        summary.add(createSummaryCard(
                "Orders", orders.size(), "▤"
        ));

        // Existing admin contents/actions are preserved.
        JPanel actions = new JPanel(
                new GridLayout(2, 2, 18, 18)
        );
        actions.setBackground(lightBackground);

        actions.add(createDashboardTile(
                "♙", "Manage Customers",
                "View registered customers and their orders",
                e -> showCustomers()
        ));

        actions.add(createDashboardTile(
                "▰", "Delivery Partners",
                "Manage delivery partners and assignments",
                e -> showDeliveryPartners()
        ));

        actions.add(createDashboardTile(
                "▣", "Manage Restaurants",
                "View and add restaurants",
                e -> showRestaurants()
        ));

        actions.add(createDashboardTile(
                "☷", "Manage Food",
                "View and add food items",
                e -> showFood()
        ));

        actions.add(createDashboardTile(
                "▤", "View Orders",
                "View all customer orders",
                e -> showOrders()
        ));

        JPanel center = new JPanel(new BorderLayout(0, 18));
        center.setBackground(lightBackground);
        center.add(summary, BorderLayout.NORTH);
        center.add(actions, BorderLayout.CENTER);

        main.add(center, BorderLayout.CENTER);
        root.add(main, BorderLayout.CENTER);

        contentPanel.add(root, BorderLayout.CENTER);
        refresh();
    }

    private JButton createAdminButton(
            String text,
            java.awt.event.ActionListener action) {

        JButton button =
                createActionButton(text);

        button.setPreferredSize(
                new Dimension(
                        330,
                        95
                )
        );

        button.addActionListener(
                action
        );

        return button;
    }

    // =========================================================
    // CUSTOMER HOME
    // =========================================================
    void showCustomerHome() {

        if (!loggedInRole.equals("CUSTOMER")) {
            showMainMenu();
            return;
        }

        contentPanel.removeAll();

        Customer current = findCustomer(currentCustomerId);
        String customerName =
                current == null ? "Customer" : current.name;

        JPanel root = createDashboardRoot();
        root.add(createDashboardSidebar("CUSTOMER"), BorderLayout.WEST);

        JPanel main = createDashboardMain(
                "Welcome, " + customerName,
                "Order food, manage your cart and track your orders"
        );

        JPanel cards = new JPanel(
                new GridLayout(2, 2, 18, 18)
        );
        cards.setBackground(lightBackground);

        cards.add(createDashboardTile(
                "⌕", "Browse Food",
                "Explore available food",
                e -> browseFood()
        ));

        cards.add(createDashboardTile(
                "□", "Cart",
                "Review items before ordering",
                e -> showCart()
        ));

        cards.add(createDashboardTile(
                "▤", "My Orders",
                "View your order history",
                e -> showCustomerOrders()
        ));

        cards.add(createDashboardTile(
                "⇥", "Logout",
                "Sign out of customer account",
                e -> loginLogout()
        ));

        main.add(cards, BorderLayout.CENTER);
        root.add(main, BorderLayout.CENTER);

        contentPanel.add(root, BorderLayout.CENTER);
        refresh();
    }

    // =========================================================
    // BROWSE FOOD
    // =========================================================

    private void browseFood() {

        contentPanel.removeAll();

        JPanel panel =
                createPage();

        panel.add(
                createHeading(
                        "BROWSE FOOD"
                ),
                BorderLayout.NORTH
        );

        DefaultTableModel model =
                new DefaultTableModel(
                        new Object[]{
                                "ID",
                                "Food",
                                "Restaurant",
                                "Price"
                        },
                        0
                );

        for (Food food : foods) {

            Restaurant restaurant =
                    findRestaurant(
                            food.restaurantId
                    );

            model.addRow(
                    new Object[]{
                            food.id,
                            food.name,
                            restaurant == null
                                    ? "Unknown"
                                    : restaurant.name,
                            "₹" + food.price
                    }
            );
        }

        JTable table =
                new JTable(model);

        table.setRowHeight(32);
        table.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );
        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );
        table.getTableHeader().setBackground(
                new Color(30, 41, 59)
        );
        table.getTableHeader().setForeground(
                Color.WHITE
        );
        table.setSelectionBackground(
                new Color(226, 236, 255)
        );
        table.setSelectionForeground(
                darkBlue
        );

        panel.add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        JButton add =
                createActionButton(
                        "Add to Cart"
                );

        JButton cartButton =
                createActionButton(
                        "View Cart"
                );

        JButton back =
                createActionButton(
                        "Back"
                );

        add.addActionListener(
                e -> {

                    int row =
                            table.getSelectedRow();

                    if (row == -1) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Please select food."
                        );

                        return;
                    }

                    int id =
                            Integer.parseInt(
                                    table.getValueAt(
                                            row,
                                            0
                                    ).toString()
                            );

                    Food food =
                            findFood(id);

                    if (food != null) {

                        cart.add(
                                new CartItem(
                                        food
                                )
                        );

                        JOptionPane.showMessageDialog(
                                this,
                                food.name +
                                        " added to cart."
                        );
                    }
                }
        );

        cartButton.addActionListener(
                e -> showCart()
        );

        back.addActionListener(
                e -> showCustomerHome()
        );

        JPanel bottom =
                new JPanel();

        bottom.setBackground(
                lightBackground
        );

        bottom.add(add);
        bottom.add(cartButton);
        bottom.add(back);

        panel.add(
                bottom,
                BorderLayout.SOUTH
        );

        contentPanel.add(
                panel,
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // CART
    // =========================================================

    private void showCart() {

        contentPanel.removeAll();

        JPanel panel =
                createPage();

        panel.add(
                createHeading(
                        "MY CART"
                ),
                BorderLayout.NORTH
        );

        DefaultTableModel model =
                new DefaultTableModel(
                        new Object[]{
                                "Food",
                                "Restaurant",
                                "Price"
                        },
                        0
                );

        double total = 0;

        for (CartItem item : cart) {

            Restaurant restaurant =
                    findRestaurant(
                            item.food.restaurantId
                    );

            model.addRow(
                    new Object[]{
                            item.food.name,
                            restaurant == null
                                    ? "Unknown"
                                    : restaurant.name,
                            "₹" +
                                    item.food.price
                    }
            );

            total +=
                    item.food.price;
        }

        JTable table =
                new JTable(model);

        table.setRowHeight(32);
        table.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );
        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );
        table.getTableHeader().setBackground(
                new Color(30, 41, 59)
        );
        table.getTableHeader().setForeground(
                Color.WHITE
        );
        table.setSelectionBackground(
                new Color(226, 236, 255)
        );
        table.setSelectionForeground(
                darkBlue
        );

        panel.add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        JLabel totalLabel =
                new JLabel(
                        String.format(
                                "Cart Total: ₹%.2f",
                                total
                        )
                );

        totalLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        totalLabel.setForeground(
                darkBlue
        );

        JButton remove =
                createActionButton(
                        "Remove"
                );

        JButton order =
                createActionButton(
                        "Place Order"
                );

        JButton back =
                createActionButton(
                        "Back"
                );

        remove.addActionListener(
                e -> {

                    int row =
                            table.getSelectedRow();

                    if (row == -1) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Select an item."
                        );

                        return;
                    }

                    cart.remove(row);

                    showCart();
                }
        );

        order.addActionListener(
                e -> placeOrderFromCart()
        );

        back.addActionListener(
                e -> showCustomerHome()
        );

        JPanel bottom =
                new JPanel();

        bottom.setBackground(
                lightBackground
        );

        bottom.add(totalLabel);
        bottom.add(remove);
        bottom.add(order);
        bottom.add(back);

        panel.add(
                bottom,
                BorderLayout.SOUTH
        );

        contentPanel.add(
                panel,
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // PLACE ORDER
    // =========================================================

    private void placeOrderFromCart() {

        if (cart.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Your cart is empty."
            );

            return;
        }

        double subtotal = 0;

        for (CartItem item : cart) {

            subtotal +=
                    item.food.price;
        }

        double deliveryFee = 30;

        double gst =
                subtotal * 0.05;

        double total =
                subtotal +
                        deliveryFee +
                        gst;

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        String.format(
                                "Subtotal: ₹%.2f\n" +
                                        "Delivery Fee: ₹%.2f\n" +
                                        "GST 5%%: ₹%.2f\n" +
                                        "--------------------\n" +
                                        "Total: ₹%.2f\n\n" +
                                        "Priority can be selected after payment method.\n\n" +
                                        "Continue to payment?",
                                subtotal,
                                deliveryFee,
                                gst,
                                total
                        ),
                        "Order Summary",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            showPayment(total);
        }
    }

    // =========================================================
    // PAYMENT
    // =========================================================

    private void showPayment(
            double total) {

        String[] methods = {

                "UPI",
                "Credit / Debit Card",
                "Cash on Delivery"
        };

        String method =
                (String)
                        JOptionPane.showInputDialog(
                                this,
                                "Select Payment Method:",
                                "Payment",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                methods,
                                methods[0]
                        );

        if (method == null) {
            return;
        }

        if (method.equals("UPI")) {

            String upi =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter UPI ID:"
                    );

            if (
                    upi == null ||
                            upi.trim().isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid UPI ID."
                );

                return;
            }
        }

        if (
                method.equals(
                        "Credit / Debit Card"
                )
        ) {

            String card =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter Card Number:"
                    );

            if (
                    card == null ||
                            card.trim().isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid card number."
                );

                return;
            }
        }

        String[] priorities = {
                "HIGH",
                "NORMAL",
                "LOW"
        };

        String priority =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Select Order Priority:",
                        "Order Priority",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        priorities,
                        priorities[1]
                );

        if (priority == null) {
            return;
        }

        for (CartItem item : cart) {

            Food food =
                    item.food;

            orders.add(
                    new Order(
                            orderId++,
                            currentCustomerId,
                            food.restaurantId,
                            food.id,
                            food.price,
                            "PENDING",
                            method,
                            priority,
                            findAvailableDeliveryPartnerId()
                    )
            );
        }

        JOptionPane.showMessageDialog(
                this,
                String.format(
                        "Payment Successful!\n\n" +
                                "Method: %s\n" +
                                "Priority: %s\n" +
                                "Amount: ₹%.2f\n\n" +
                                "Order placed successfully!",
                        method,
                        priority,
                        total
                ),
                "Payment Successful",
                JOptionPane.INFORMATION_MESSAGE
        );

        cart.clear();

        showCustomerOrders();
    }

    // =========================================================
    // CUSTOMER ORDERS
    // =========================================================

    private void showCustomerOrders() {

        contentPanel.removeAll();

        JPanel panel =
                createPage();

        panel.add(
                createHeading(
                        "MY ORDERS"
                ),
                BorderLayout.NORTH
        );

        DefaultTableModel model =
                new DefaultTableModel(
                        new Object[]{
                                "Order ID",
                                "Food",
                                "Restaurant",
                                "Amount",
                                "Payment",
                                "Priority",
                                "Delivery Partner",
                                "Status"
                        },
                        0
                );

        for (Order order : orders) {

            if (order.customerId != currentCustomerId) {
                continue;
            }

            Food food =
                    findFood(
                            order.foodId
                    );

            Restaurant restaurant =
                    findRestaurant(
                            order.restaurantId
                    );

            model.addRow(
                    new Object[]{
                            order.id,
                            food == null
                                    ? ""
                                    : food.name,
                            restaurant == null
                                    ? ""
                                    : restaurant.name,
                            "₹" +
                                    order.amount,
                            order.paymentMethod,
                            order.priority,
                            findDeliveryPartner(order.deliveryPartnerId) == null
                                    ? "Unassigned"
                                    : findDeliveryPartner(order.deliveryPartnerId).name,
                            order.status
                    }
            );
        }

        JTable table =
                new JTable(model);

        table.setRowHeight(32);
        table.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );
        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );
        table.getTableHeader().setBackground(
                new Color(30, 41, 59)
        );
        table.getTableHeader().setForeground(
                Color.WHITE
        );
        table.setSelectionBackground(
                new Color(226, 236, 255)
        );
        table.setSelectionForeground(
                darkBlue
        );

        panel.add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        JButton back =
                createActionButton(
                        "Back"
                );

        back.addActionListener(
                e -> showCustomerHome()
        );

        JPanel bottom =
                new JPanel();

        bottom.setBackground(
                lightBackground
        );

        bottom.add(back);

        panel.add(
                bottom,
                BorderLayout.SOUTH
        );

        contentPanel.add(
                panel,
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // DELIVERY PARTNER HOME
    // =========================================================

    private void showDeliveryPartnerHome() {

        if (!loggedInRole.equals("DELIVERY PARTNER")) {
            showMainMenu();
            return;
        }

        contentPanel.removeAll();

        JPanel root = createDashboardRoot();
        root.add(createDashboardSidebar("DELIVERY"), BorderLayout.WEST);

        DeliveryPartner partner = findDeliveryPartner(currentDeliveryPartnerId);
        String partnerName = partner == null ? "Delivery Partner" : partner.name;

        JPanel main = createDashboardMain(
                "Welcome, " + partnerName,
                "Manage assigned deliveries and update delivery status"
        );

        int assigned = 0;
        for (Order order : orders) {
            if (order.deliveryPartnerId == currentDeliveryPartnerId) {
                assigned++;
            }
        }

        JPanel cards = new JPanel(new GridLayout(1, 4, 18, 18));
        cards.setBackground(lightBackground);
        cards.add(createSummaryCard("Assigned Orders", assigned, "▤"));
        cards.add(createSummaryCard("Partners", deliveryPartners.size(), "▰"));
        cards.add(createSummaryCard("Delivered", countDeliveredForPartner(), "✓"));
        cards.add(createSummaryCard("High Priority", countHighPriorityForPartner(), "!"));

        JPanel center = new JPanel(new BorderLayout(0, 18));
        center.setBackground(lightBackground);
        center.add(cards, BorderLayout.NORTH);

        JPanel actions = new JPanel(new GridLayout(1, 2, 18, 18));
        actions.setBackground(lightBackground);
        actions.add(createDashboardTile(
                "▤", "My Deliveries",
                "View orders assigned to you",
                e -> showDeliveryPartnerOrders()
        ));
        actions.add(createDashboardTile(
                "↻", "Update Delivery Status",
                "Update the delivery progress of an order",
                e -> updateDeliveryStatus()
        ));

        center.add(actions, BorderLayout.CENTER);
        main.add(center, BorderLayout.CENTER);
        root.add(main, BorderLayout.CENTER);

        contentPanel.add(root, BorderLayout.CENTER);
        refresh();
    }

    private void showDeliveryPartnerOrders() {

        contentPanel.removeAll();

        JPanel panel = createPage();
        panel.add(createHeading("MY DELIVERIES"), BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel(
                new Object[]{
                        "Order ID", "Customer", "Food", "Restaurant",
                        "Amount", "Payment", "Priority", "Status"
                }, 0
        );

        for (Order order : orders) {
            if (order.deliveryPartnerId != currentDeliveryPartnerId) {
                continue;
            }

            Customer customer = findCustomer(order.customerId);
            Food food = findFood(order.foodId);
            Restaurant restaurant = findRestaurant(order.restaurantId);

            model.addRow(new Object[]{
                    order.id,
                    customer == null ? "" : customer.name,
                    food == null ? "" : food.name,
                    restaurant == null ? "" : restaurant.name,
                    "₹" + order.amount,
                    order.paymentMethod,
                    order.priority,
                    order.status
            });
        }

        JTable table = new JTable(model);
        table.setRowHeight(32);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(30, 41, 59));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(226, 236, 255));
        table.setSelectionForeground(darkBlue);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JButton update = createActionButton("Update Status");
        JButton back = createActionButton("Back");

        update.addActionListener(e -> updateDeliveryStatus());
        back.addActionListener(e -> showDeliveryPartnerHome());

        JPanel bottom = new JPanel();
        bottom.setBackground(lightBackground);
        bottom.add(update);
        bottom.add(back);
        panel.add(bottom, BorderLayout.SOUTH);

        contentPanel.add(panel, BorderLayout.CENTER);
        refresh();
    }

    private void updateDeliveryStatus() {

        List<Order> assignedOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.deliveryPartnerId == currentDeliveryPartnerId) {
                assignedOrders.add(order);
            }
        }

        if (assignedOrders.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No deliveries are assigned to you.");
            return;
        }

        String input = JOptionPane.showInputDialog(this, "Enter Order ID:");
        if (input == null) {
            return;
        }

        try {
            int id = Integer.parseInt(input.trim());
            Order order = findOrder(id);

            if (order == null || order.deliveryPartnerId != currentDeliveryPartnerId) {
                JOptionPane.showMessageDialog(this, "This order is not assigned to you.");
                return;
            }

            String[] statuses = {
                    "PICKED UP",
                    "OUT FOR DELIVERY",
                    "DELIVERED",
                    "CANCELLED"
            };

            String status = (String) JOptionPane.showInputDialog(
                    this,
                    "Select delivery status:",
                    "Update Delivery Status",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    statuses,
                    statuses[0]
            );

            if (status == null) {
                return;
            }

            order.status = status;

            JOptionPane.showMessageDialog(
                    this,
                    "Order #" + id + " updated to " + status
            );

            showDeliveryPartnerOrders();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Order ID.");
        }
    }

    private int countHighPriorityForPartner() {
        int count = 0;
        for (Order order : orders) {
            if (order.deliveryPartnerId == currentDeliveryPartnerId
                    && "HIGH".equals(order.priority)
                    && !"DELIVERED".equals(order.status)
                    && !"CANCELLED".equals(order.status)) {
                count++;
            }
        }
        return count;
    }

    private int findAvailableDeliveryPartnerId() {

        if (deliveryPartners.isEmpty()) {
            return -1;
        }

        int bestId = deliveryPartners.get(0).id;
        int bestCount = Integer.MAX_VALUE;

        for (DeliveryPartner partner : deliveryPartners) {
            int count = 0;

            for (Order order : orders) {
                if (order.deliveryPartnerId == partner.id
                        && !order.status.equals("DELIVERED")
                        && !order.status.equals("CANCELLED")) {
                    count++;
                }
            }

            if (count < bestCount) {
                bestCount = count;
                bestId = partner.id;
            }
        }

        return bestId;
    }

    private int countDeliveredForPartner() {
        int count = 0;
        for (Order order : orders) {
            if (order.deliveryPartnerId == currentDeliveryPartnerId
                    && order.status.equals("DELIVERED")) {
                count++;
            }
        }
        return count;
    }

    private DeliveryPartner findDeliveryPartner(int id) {
        for (DeliveryPartner partner : deliveryPartners) {
            if (partner.id == id) {
                return partner;
            }
        }
        return null;
    }

    // =========================================================
    // RESTAURANT HOME
    // =========================================================

    private void showRestaurantHome() {

        if (!loggedInRole.equals("RESTAURANT")) {
            showMainMenu();
            return;
        }

        contentPanel.removeAll();

        JPanel root = createDashboardRoot();
        root.add(createDashboardSidebar("RESTAURANT"), BorderLayout.WEST);

        JPanel main = createDashboardMain(
                "Restaurant Dashboard",
                "Manage your restaurant, orders and delivery status"
        );

        JPanel cards = new JPanel(
                new GridLayout(2, 2, 18, 18)
        );
        cards.setBackground(lightBackground);

        cards.add(createDashboardTile(
                "☷", "Manage Menu",
                "Add or remove food items",
                e -> restaurantManageMenu()
        ));

        cards.add(createDashboardTile(
                "▤", "View Orders",
                "View customer orders",
                e -> restaurantViewOrders()
        ));

        cards.add(createDashboardTile(
                "↻", "Update Order Status",
                "Update order progress",
                e -> restaurantUpdateStatus()
        ));

        cards.add(createDashboardTile(
                "⇥", "Logout",
                "Sign out of restaurant account",
                e -> loginLogout()
        ));

        main.add(cards, BorderLayout.CENTER);
        root.add(main, BorderLayout.CENTER);

        contentPanel.add(root, BorderLayout.CENTER);
        refresh();
    }

    // =========================================================
    // RESTAURANT MANAGE MENU
    // =========================================================

    private void restaurantManageMenu() {

        contentPanel.removeAll();

        JPanel panel =
                createPage();

        panel.add(
                createHeading(
                        "MANAGE MENU"
                ),
                BorderLayout.NORTH
        );

        DefaultTableModel model =
                new DefaultTableModel(
                        new Object[]{
                                "ID",
                                "Food",
                                "Price"
                        },
                        0
                );

        for (Food food : foods) {

            model.addRow(
                    new Object[]{
                            food.id,
                            food.name,
                            "₹" +
                                    food.price
                    }
            );
        }

        JTable table =
                new JTable(model);

        table.setRowHeight(32);
        table.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );
        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );
        table.getTableHeader().setBackground(
                new Color(30, 41, 59)
        );
        table.getTableHeader().setForeground(
                Color.WHITE
        );
        table.setSelectionBackground(
                new Color(226, 236, 255)
        );
        table.setSelectionForeground(
                darkBlue
        );

        panel.add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        JButton add =
                createActionButton(
                        "Add Food"
                );

        JButton delete =
                createActionButton(
                        "Delete Food"
                );

        JButton back =
                createActionButton(
                        "Back"
                );

        add.addActionListener(
                e -> restaurantAddFood()
        );

        delete.addActionListener(
                e -> {

                    int row =
                            table.getSelectedRow();

                    if (row == -1) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Select food."
                        );

                        return;
                    }

                    int id =
                            Integer.parseInt(
                                    table.getValueAt(
                                            row,
                                            0
                                    ).toString()
                            );

                    Food food =
                            findFood(id);

                    if (food != null) {

                        foods.remove(food);

                        restaurantManageMenu();
                    }
                }
        );

        back.addActionListener(
                e -> showRestaurantHome()
        );

        JPanel bottom =
                new JPanel();

        bottom.setBackground(
                lightBackground
        );

        bottom.add(add);
        bottom.add(delete);
        bottom.add(back);

        panel.add(
                bottom,
                BorderLayout.SOUTH
        );

        contentPanel.add(
                panel,
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // RESTAURANT ADD FOOD
    // =========================================================

    private void restaurantAddFood() {

        JTextField name =
                new JTextField();

        JTextField price =
                new JTextField();

        Object[] fields = {

                "Food Name:",
                name,

                "Price:",
                price
        };

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        fields,
                        "Add Food",
                        JOptionPane.OK_CANCEL_OPTION
                );

        if (
                result !=
                        JOptionPane.OK_OPTION
        ) {

            return;
        }

        try {

            String foodName =
                    name.getText().trim();

            double foodPrice =
                    Double.parseDouble(
                            price.getText().trim()
                    );

            if (
                    foodName.isEmpty()
                            ||
                            foodPrice <= 0
            ) {

                throw new Exception();
            }

            foods.add(
                    new Food(
                            foodId++,
                            foodName,
                            foodPrice,
                            1
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Food added successfully."
            );

            restaurantManageMenu();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter valid food details."
            );
        }
    }

    // =========================================================
    // RESTAURANT ORDERS
    // =========================================================

    private void restaurantViewOrders() {

        contentPanel.removeAll();

        JPanel panel =
                createPage();

        panel.add(
                createHeading(
                        "RESTAURANT ORDERS"
                ),
                BorderLayout.NORTH
        );

        DefaultTableModel model =
                new DefaultTableModel(
                        new Object[]{
                                "Order ID",
                                "Food",
                                "Amount",
                                "Payment",
                                "Priority",
                                "Delivery Partner",
                                "Status"
                        },
                        0
                );

        for (Order order : orders) {

            Food food =
                    findFood(
                            order.foodId
                    );

            model.addRow(
                    new Object[]{
                            order.id,
                            food == null
                                    ? ""
                                    : food.name,
                            "₹" +
                                    order.amount,
                            order.paymentMethod,
                            order.priority,
                            findDeliveryPartner(order.deliveryPartnerId) == null
                                    ? "Unassigned"
                                    : findDeliveryPartner(order.deliveryPartnerId).name,
                            order.status
                    }
            );
        }

        JTable table =
                new JTable(model);

        table.setRowHeight(32);
        table.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );
        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );
        table.getTableHeader().setBackground(
                new Color(30, 41, 59)
        );
        table.getTableHeader().setForeground(
                Color.WHITE
        );
        table.setSelectionBackground(
                new Color(226, 236, 255)
        );
        table.setSelectionForeground(
                darkBlue
        );

        panel.add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        JButton update =
                createActionButton(
                        "Update Status"
                );

        JButton back =
                createActionButton(
                        "Back"
                );

        update.addActionListener(
                e -> restaurantUpdateStatus()
        );

        back.addActionListener(
                e -> showRestaurantHome()
        );

        JPanel bottom =
                new JPanel();

        bottom.setBackground(
                lightBackground
        );

        bottom.add(update);
        bottom.add(back);

        panel.add(
                bottom,
                BorderLayout.SOUTH
        );

        contentPanel.add(
                panel,
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // UPDATE ORDER STATUS
    // =========================================================

    private void restaurantUpdateStatus() {

        if (orders.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No orders available."
            );

            return;
        }

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Order ID:"
                );

        if (input == null) {
            return;
        }

        try {

            int id =
                    Integer.parseInt(
                            input.trim()
                    );

            Order order =
                    findOrder(id);

            if (order == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Order not found."
                );

                return;
            }

            String[] statuses = {

                    "CONFIRMED",
                    "PREPARING",
                    "OUT FOR DELIVERY",
                    "DELIVERED",
                    "CANCELLED"
            };

            String status =
                    (String)
                            JOptionPane.showInputDialog(
                                    this,
                                    "Select status:",
                                    "Update Order Status",
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    statuses,
                                    statuses[0]
                            );

            if (status == null) {
                return;
            }

            order.status =
                    status;

            JOptionPane.showMessageDialog(
                    this,
                    "Order #" +
                            id +
                            " updated to " +
                            status
            );

            restaurantViewOrders();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Order ID."
            );
        }
    }

    // =========================================================
    // CUSTOMERS
    // =========================================================

    private void showCustomers() {

        contentPanel.removeAll();

        JPanel panel = createPage();

        panel.add(
                createHeading("MANAGE CUSTOMERS"),
                BorderLayout.NORTH
        );

        DefaultTableModel model =
                new DefaultTableModel(
                        new Object[]{
                                "ID",
                                "Name",
                                "Location",
                                "Username",
                                "Orders"
                        },
                        0
                );

        for (Customer c : customers) {

            int customerOrders = 0;

            for (Order order : orders) {
                if (order.customerId == c.id) {
                    customerOrders++;
                }
            }

            model.addRow(
                    new Object[]{
                            c.id,
                            c.name,
                            c.location,
                            c.username,
                            customerOrders
                    }
            );
        }

        JTable table = new JTable(model);
        table.setRowHeight(30);

        panel.add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        JButton viewCustomer =
                createActionButton("View Customer");

        JButton viewOrders =
                createActionButton("View Customer Orders");

        JButton back =
                createActionButton("Back");

        viewCustomer.addActionListener(
                e -> showCustomers()
        );

        viewOrders.addActionListener(
                e -> {
                    int row = table.getSelectedRow();

                    if (row == -1) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Please select a customer."
                        );
                        return;
                    }

                    int id = Integer.parseInt(
                            table.getValueAt(row, 0).toString()
                    );

                    showCustomerOrdersForAdmin(id);
                }
        );

        back.addActionListener(
                e -> showAdminOperations()
        );

        JPanel bottom = new JPanel();
        bottom.setBackground(lightBackground);

        bottom.add(viewCustomer);
        bottom.add(viewOrders);
        bottom.add(back);

        panel.add(
                bottom,
                BorderLayout.SOUTH
        );

        contentPanel.add(
                panel,
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // DELIVERY PARTNERS - ADMIN
    // =========================================================

    private void showDeliveryPartners() {

        contentPanel.removeAll();

        JPanel panel = createPage();
        panel.add(createHeading("DELIVERY PARTNERS"), BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Name", "Location", "Username", "Assigned Orders"},
                0
        );

        for (DeliveryPartner partner : deliveryPartners) {
            int assigned = 0;
            for (Order order : orders) {
                if (order.deliveryPartnerId == partner.id) {
                    assigned++;
                }
            }
            model.addRow(new Object[]{
                    partner.id, partner.name, partner.location,
                    partner.username, assigned
            });
        }

        JTable table = new JTable(model);
        table.setRowHeight(32);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(30, 41, 59));
        table.getTableHeader().setForeground(Color.WHITE);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JButton add = createActionButton("Add Delivery Partner");
        JButton back = createActionButton("Back");

        add.addActionListener(e -> addDeliveryPartner());
        back.addActionListener(e -> showAdminOperations());

        JPanel bottom = new JPanel();
        bottom.setBackground(lightBackground);
        bottom.add(add);
        bottom.add(back);
        panel.add(bottom, BorderLayout.SOUTH);

        contentPanel.add(panel, BorderLayout.CENTER);
        refresh();
    }

    private void addDeliveryPartner() {

        JTextField name = new JTextField();
        JTextField location = new JTextField();
        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();

        Object[] fields = {
                "Partner Name:", name,
                "Location:", location,
                "Username:", username,
                "Password:", password
        };

        int result = JOptionPane.showConfirmDialog(
                this, fields, "Add Delivery Partner",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String partnerName = name.getText().trim();
        String partnerLocation = location.getText().trim();
        String partnerUsername = username.getText().trim();
        String partnerPassword = new String(password.getPassword());

        if (partnerName.isEmpty() || partnerUsername.isEmpty()
                || partnerPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields.");
            return;
        }

        for (DeliveryPartner partner : deliveryPartners) {
            if (partner.username.equalsIgnoreCase(partnerUsername)) {
                JOptionPane.showMessageDialog(this, "Username already exists.");
                return;
            }
        }

        deliveryPartners.add(new DeliveryPartner(
                deliveryPartnerId++, partnerName, partnerLocation,
                partnerUsername, partnerPassword
        ));

        JOptionPane.showMessageDialog(this, "Delivery partner added successfully.");
        showDeliveryPartners();
    }

    // =========================================================
    // CUSTOMER REGISTRATION
    // =========================================================

    private void registerCustomer() {

        JTextField name = new JTextField();
        JTextField location = new JTextField();
        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();
        JPasswordField confirmPassword = new JPasswordField();

        Object[] fields = {
                "Customer Name:", name,
                "Location:", location,
                "Username:", username,
                "Password:", password,
                "Confirm Password:", confirmPassword
        };

        int result = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Customer Registration",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String customerName = name.getText().trim();
        String customerLocation = location.getText().trim();
        String customerUsername = username.getText().trim();
        String customerPassword = new String(password.getPassword());
        String confirm = new String(confirmPassword.getPassword());

        if (customerName.isEmpty()
                || customerUsername.isEmpty()
                || customerPassword.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all required fields."
            );

            return;
        }

        if (!customerPassword.equals(confirm)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Passwords do not match."
            );

            return;
        }

        if (findCustomerByUsername(customerUsername) != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Username already exists. Please choose another username."
            );

            return;
        }

        Customer customer = new Customer(
                customerId++,
                customerName,
                customerLocation,
                customerUsername,
                customerPassword
        );

        customers.add(customer);

        JOptionPane.showMessageDialog(
                this,
                "Registration successful!\nYou can now login with your new account."
        );
    }

    // =========================================================
    // CUSTOMER AUTHENTICATION
    // =========================================================

    private Customer authenticateCustomer() {

        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();

        Object[] fields = {
                "Username:", username,
                "Password:", password
        };

        int result = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Customer Login",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return null;
        }

        String enteredUsername = username.getText().trim();
        String enteredPassword =
                new String(password.getPassword());

        Customer customer =
                findCustomerByUsername(enteredUsername);

        if (customer != null
                && customer.password.equals(enteredPassword)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Customer login successful!"
            );

            return customer;
        }

        JOptionPane.showMessageDialog(
                this,
                "Invalid username or password.",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE
        );

        return null;
    }

    private Customer findCustomerByUsername(String username) {

        for (Customer c : customers) {

            if (c.username.equalsIgnoreCase(username)) {
                return c;
            }
        }

        return null;
    }

    // =========================================================
    // ADMIN - VIEW SELECTED CUSTOMER ORDERS
    // =========================================================

    private void showCustomerOrdersForAdmin(int customerId) {

        Customer customer = findCustomer(customerId);

        if (customer == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Customer not found."
            );
            return;
        }

        contentPanel.removeAll();

        JPanel panel = createPage();

        panel.add(
                createHeading(
                        "ORDERS - " + customer.name
                ),
                BorderLayout.NORTH
        );

        DefaultTableModel model =
                new DefaultTableModel(
                        new Object[]{
                                "Order ID",
                                "Food",
                                "Restaurant",
                                "Amount",
                                "Payment",
                                "Priority",
                                "Status"
                        },
                        0
                );

        for (Order order : orders) {

            if (order.customerId != customerId) {
                continue;
            }

            Food food = findFood(order.foodId);
            Restaurant restaurant =
                    findRestaurant(order.restaurantId);

            model.addRow(
                    new Object[]{
                            order.id,
                            food == null ? "" : food.name,
                            restaurant == null
                                    ? ""
                                    : restaurant.name,
                            "₹" + order.amount,
                            order.paymentMethod,
                            order.priority,
                            order.status
                    }
            );
        }

        JTable table = new JTable(model);
        table.setRowHeight(30);

        panel.add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        JButton back = createActionButton("Back");

        back.addActionListener(
                e -> showCustomers()
        );

        JPanel bottom = new JPanel();
        bottom.setBackground(lightBackground);
        bottom.add(back);

        panel.add(
                bottom,
                BorderLayout.SOUTH
        );

        contentPanel.add(
                panel,
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // RESTAURANTS
    // =========================================================

    private void showRestaurants() {

        contentPanel.removeAll();

        JPanel panel =
                createPage();

        panel.add(
                createHeading(
                        "RESTAURANTS"
                ),
                BorderLayout.NORTH
        );

        DefaultTableModel model =
                new DefaultTableModel(
                        new Object[]{
                                "ID",
                                "Restaurant",
                                "Location"
                        },
                        0
                );

        for (Restaurant r : restaurants) {

            model.addRow(
                    new Object[]{
                            r.id,
                            r.name,
                            r.location
                    }
            );
        }

        JTable table =
                new JTable(model);

        table.setRowHeight(32);
        table.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );
        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );
        table.getTableHeader().setBackground(
                new Color(30, 41, 59)
        );
        table.getTableHeader().setForeground(
                Color.WHITE
        );
        table.setSelectionBackground(
                new Color(226, 236, 255)
        );
        table.setSelectionForeground(
                darkBlue
        );

        panel.add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        JButton add =
                createActionButton(
                        "Add Restaurant"
                );

        JButton back =
                createActionButton(
                        "Back"
                );

        add.addActionListener(
                e -> addRestaurant()
        );

        back.addActionListener(
                e -> showAdminOperations()
        );

        JPanel bottom =
                new JPanel();

        bottom.setBackground(
                lightBackground
        );

        bottom.add(add);
        bottom.add(back);

        panel.add(
                bottom,
                BorderLayout.SOUTH
        );

        contentPanel.add(
                panel,
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // ADD RESTAURANT
    // =========================================================

    private void addRestaurant() {

        if (
                !loggedInRole.equals(
                        "ADMIN"
                )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Only Admin can add restaurants."
            );

            return;
        }

        JTextField name =
                new JTextField();

        JTextField location =
                new JTextField();

        Object[] fields = {

                "Restaurant Name:",
                name,

                "Location:",
                location
        };

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        fields,
                        "Add Restaurant",
                        JOptionPane.OK_CANCEL_OPTION
                );

        if (
                result ==
                        JOptionPane.OK_OPTION
        ) {

            String restaurantName =
                    name.getText().trim();

            if (restaurantName.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter restaurant name."
                );

                return;
            }

            restaurants.add(
                    new Restaurant(
                            restaurantId++,
                            restaurantName,
                            location.getText().trim()
                    )
            );

            showRestaurants();
        }
    }

    // =========================================================
    // FOOD
    // =========================================================

    private void showFood() {

        contentPanel.removeAll();

        JPanel panel =
                createPage();

        panel.add(
                createHeading(
                        "FOOD MENU"
                ),
                BorderLayout.NORTH
        );

        DefaultTableModel model =
                new DefaultTableModel(
                        new Object[]{
                                "ID",
                                "Food",
                                "Price",
                                "Restaurant"
                        },
                        0
                );

        for (Food food : foods) {

            Restaurant restaurant =
                    findRestaurant(
                            food.restaurantId
                    );

            model.addRow(
                    new Object[]{
                            food.id,
                            food.name,
                            "₹" +
                                    food.price,
                            restaurant == null
                                    ? ""
                                    : restaurant.name
                    }
            );
        }

        JTable table =
                new JTable(model);

        table.setRowHeight(32);
        table.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );
        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );
        table.getTableHeader().setBackground(
                new Color(30, 41, 59)
        );
        table.getTableHeader().setForeground(
                Color.WHITE
        );
        table.setSelectionBackground(
                new Color(226, 236, 255)
        );
        table.setSelectionForeground(
                darkBlue
        );

        panel.add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        JButton add =
                createActionButton(
                        "Add Food"
                );

        JButton back =
                createActionButton(
                        "Back"
                );

        add.addActionListener(
                e -> addFood()
        );

        back.addActionListener(
                e -> showAdminOperations()
        );

        JPanel bottom =
                new JPanel();

        bottom.setBackground(
                lightBackground
        );

        bottom.add(add);
        bottom.add(back);

        panel.add(
                bottom,
                BorderLayout.SOUTH
        );

        contentPanel.add(
                panel,
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // ADD FOOD
    // =========================================================

    private void addFood() {

        if (
                !loggedInRole.equals(
                        "ADMIN"
                )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Only Admin can add food."
            );

            return;
        }

        JTextField name =
                new JTextField();

        JTextField price =
                new JTextField();

        JComboBox<String> restaurantBox =
                new JComboBox<>();

        for (Restaurant r : restaurants) {

            restaurantBox.addItem(
                    r.id +
                            " - " +
                            r.name
            );
        }

        Object[] fields = {

                "Food Name:",
                name,

                "Price:",
                price,

                "Restaurant:",
                restaurantBox
        };

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        fields,
                        "Add Food",
                        JOptionPane.OK_CANCEL_OPTION
                );

        if (
                result !=
                        JOptionPane.OK_OPTION
        ) {

            return;
        }

        try {

            String foodName =
                    name.getText().trim();

            double foodPrice =
                    Double.parseDouble(
                            price.getText().trim()
                    );

            if (
                    foodName.isEmpty()
                            ||
                            foodPrice <= 0
            ) {

                throw new Exception();
            }

            if (
                    restaurantBox.getSelectedItem()
                            == null
            ) {

                throw new Exception();
            }

            String selected =
                    restaurantBox
                            .getSelectedItem()
                            .toString();

            int restaurantID =
                    Integer.parseInt(
                            selected.split(
                                    " - "
                            )[0]
                    );

            foods.add(
                    new Food(
                            foodId++,
                            foodName,
                            foodPrice,
                            restaurantID
                    )
            );

            showFood();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter valid food details."
            );
        }
    }

    // =========================================================
    // ORDERS
    // =========================================================

    private void showOrders() {

        contentPanel.removeAll();

        JPanel panel =
                createPage();

        panel.add(
                createHeading(
                        "ALL ORDERS"
                ),
                BorderLayout.NORTH
        );

        DefaultTableModel model =
                new DefaultTableModel(
                        new Object[]{
                                "Order ID",
                                "Customer",
                                "Food",
                                "Restaurant",
                                "Amount",
                                "Delivery Partner",
                                "Status"
                        },
                        0
                );

        for (Order order : orders) {

            Customer customer =
                    findCustomer(
                            order.customerId
                    );

            Food food =
                    findFood(
                            order.foodId
                    );

            Restaurant restaurant =
                    findRestaurant(
                            order.restaurantId
                    );

            model.addRow(
                    new Object[]{
                            order.id,
                            customer == null
                                    ? ""
                                    : customer.name,
                            food == null
                                    ? ""
                                    : food.name,
                            restaurant == null
                                    ? ""
                                    : restaurant.name,
                            "₹" +
                                    order.amount,
                            findDeliveryPartner(order.deliveryPartnerId) == null
                                    ? "Unassigned"
                                    : findDeliveryPartner(order.deliveryPartnerId).name,
                            order.status
                    }
            );
        }

        JTable table =
                new JTable(model);

        table.setRowHeight(32);
        table.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );
        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );
        table.getTableHeader().setBackground(
                new Color(30, 41, 59)
        );
        table.getTableHeader().setForeground(
                Color.WHITE
        );
        table.setSelectionBackground(
                new Color(226, 236, 255)
        );
        table.setSelectionForeground(
                darkBlue
        );

        panel.add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        JButton back =
                createActionButton(
                        "Back"
                );

        back.addActionListener(
                e -> showAdminOperations()
        );

        JPanel bottom =
                new JPanel();

        bottom.setBackground(
                lightBackground
        );

        bottom.add(back);

        panel.add(
                bottom,
                BorderLayout.SOUTH
        );

        contentPanel.add(
                panel,
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // DASHBOARD
    // =========================================================

    private void showDashboard() {

        contentPanel.removeAll();

        JPanel panel =
                createPage();

        panel.add(
                createHeading(
                        "DASHBOARD"
                ),
                BorderLayout.NORTH
        );

        JPanel cards =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                20,
                                20
                        )
                );

        cards.setBackground(
                lightBackground
        );

        cards.add(
                createCard(
                        "Customers",
                        customers.size()
                )
        );

        cards.add(
                createCard(
                        "Restaurants",
                        restaurants.size()
                )
        );

        cards.add(
                createCard(
                        "Food Items",
                        foods.size()
                )
        );

        cards.add(
                createCard(
                        "Orders",
                        orders.size()
                )
        );

        panel.add(
                cards,
                BorderLayout.CENTER
        );

        contentPanel.add(
                panel,
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // DASHBOARD CARD
    // =========================================================

    private JPanel createCard(
            String title,
            int value) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(226, 232, 240),
                                1
                        ),
                        new EmptyBorder(
                                25,
                                25,
                                25,
                                25
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        titleLabel.setForeground(
                darkBlue
        );

        JLabel valueLabel =
                new JLabel(
                        String.valueOf(
                                value
                        )
                );

        valueLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        32
                )
        );

        valueLabel.setForeground(
                blue
        );

        card.add(
                titleLabel,
                BorderLayout.NORTH
        );

        card.add(
                valueLabel,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // DASHBOARD ROOT
    // =========================================================

    private JPanel createDashboardRoot() {

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(lightBackground);
        return root;
    }

    // =========================================================
    // DASHBOARD SIDEBAR
    // =========================================================

    private JPanel createDashboardSidebar(String role) {

        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(30, 41, 59));
        sidebar.setPreferredSize(new Dimension(235, 0));
        sidebar.setBorder(
                BorderFactory.createMatteBorder(
                        0, 0, 0, 1,
                        new Color(226, 232, 240)
                )
        );

        sidebar.setLayout(
                new BoxLayout(sidebar, BoxLayout.Y_AXIS)
        );

        JLabel logo = new JLabel("Mealix");
        logo.setFont(
                new Font("Arial", Font.BOLD, 30)
        );
        logo.setForeground(Color.WHITE);
        logo.setBorder(
                new EmptyBorder(25, 22, 2, 10)
        );
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel roleLabel = new JLabel(
                role.equals("ADMIN")
                        ? "ADMIN PANEL"
                        : role.equals("CUSTOMER")
                        ? "CUSTOMER PANEL"
                        : role.equals("DELIVERY")
                        ? "DELIVERY PANEL"
                        : "RESTAURANT PANEL"
        );
        roleLabel.setFont(
                new Font("Arial", Font.BOLD, 11)
        );
        roleLabel.setForeground(new Color(45, 212, 191));
        roleLabel.setBorder(
                new EmptyBorder(0, 23, 25, 10)
        );
        roleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(logo);
        sidebar.add(roleLabel);

        sidebar.add(createSidebarItem(
                "⌂", "Dashboard",
                e -> {
                    if (role.equals("ADMIN")) showAdminOperations();
                    else if (role.equals("CUSTOMER")) showCustomerHome();
                    else if (role.equals("DELIVERY")) showDeliveryPartnerHome();
                    else showRestaurantHome();
                }
        ));

        if (role.equals("ADMIN")) {

            sidebar.add(createSidebarItem(
                    "♙", "Manage Customers",
                    e -> showCustomers()
            ));

            sidebar.add(createSidebarItem(
                    "▰", "Delivery Partners",
                    e -> showDeliveryPartners()
            ));

            sidebar.add(createSidebarItem(
                    "▣", "Manage Restaurants",
                    e -> showRestaurants()
            ));

            sidebar.add(createSidebarItem(
                    "☷", "Manage Food",
                    e -> showFood()
            ));

            sidebar.add(createSidebarItem(
                    "▤", "View Orders",
                    e -> showOrders()
            ));

        } else if (role.equals("CUSTOMER")) {

            sidebar.add(createSidebarItem(
                    "⌕", "Browse Food",
                    e -> browseFood()
            ));

            sidebar.add(createSidebarItem(
                    "□", "Cart",
                    e -> showCart()
            ));

            sidebar.add(createSidebarItem(
                    "▤", "My Orders",
                    e -> showCustomerOrders()
            ));

        } else if (role.equals("DELIVERY")) {

            sidebar.add(createSidebarItem(
                    "▤", "My Deliveries",
                    e -> showDeliveryPartnerOrders()
            ));

            sidebar.add(createSidebarItem(
                    "↻", "Update Delivery Status",
                    e -> updateDeliveryStatus()
            ));

        } else {

            sidebar.add(createSidebarItem(
                    "☷", "Manage Menu",
                    e -> restaurantManageMenu()
            ));

            sidebar.add(createSidebarItem(
                    "▤", "View Orders",
                    e -> restaurantViewOrders()
            ));

            sidebar.add(createSidebarItem(
                    "↻", "Update Order Status",
                    e -> restaurantUpdateStatus()
            ));
        }

        sidebar.add(Box.createVerticalGlue());

        sidebar.add(createSidebarItem(
                "⇥", "Logout",
                e -> loginLogout()
        ));

        sidebar.add(Box.createVerticalStrut(15));

        return sidebar;
    }

    private JButton createSidebarItem(
            String icon,
            String text,
            java.awt.event.ActionListener action) {

        JButton button = new JButton();

        button.setLayout(new BorderLayout(12, 0));
        button.setBackground(new Color(30, 41, 59));
        button.setForeground(darkBlue);
        button.setFocusPainted(false);
        button.setBorder(
                new EmptyBorder(13, 22, 13, 15)
        );
        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );
        button.setMaximumSize(
                new Dimension(235, 52)
        );
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.addActionListener(action);

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(
                new Font(
                        "Segoe UI Symbol",
                        Font.PLAIN,
                        21
                )
        );
        iconLabel.setForeground(new Color(45, 212, 191));

        JLabel textLabel = new JLabel(text);
        textLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );
        textLabel.setForeground(new Color(226, 232, 240));

        button.add(iconLabel, BorderLayout.WEST);
        button.add(textLabel, BorderLayout.CENTER);

        return button;
    }

    // =========================================================
    // DASHBOARD MAIN
    // =========================================================

    private JPanel createDashboardMain(
            String title,
            String subtitle) {

        JPanel main = new JPanel(
                new BorderLayout(0, 22)
        );
        main.setBackground(lightBackground);
        main.setBorder(
                new EmptyBorder(28, 30, 28, 30)
        );

        JPanel top = new JPanel(
                new BorderLayout()
        );
        top.setBackground(lightBackground);

        JPanel titleBox = new JPanel();
        titleBox.setBackground(lightBackground);
        titleBox.setLayout(
                new BoxLayout(
                        titleBox,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel heading = new JLabel(title);
        heading.setFont(
                new Font("Arial", Font.BOLD, 28)
        );
        heading.setForeground(darkBlue);

        JLabel sub = new JLabel(subtitle);
        sub.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );
        sub.setForeground(
                new Color(100, 116, 139)
        );

        titleBox.add(heading);
        titleBox.add(Box.createVerticalStrut(5));
        titleBox.add(sub);

        top.add(titleBox, BorderLayout.WEST);

        JLabel status = new JLabel(
                loggedInRole + "  •  Logged In"
        );
        status.setFont(
                new Font("Arial", Font.BOLD, 13)
        );
        status.setForeground(
                new Color(100, 116, 139)
        );

        top.add(status, BorderLayout.EAST);

        main.add(top, BorderLayout.NORTH);

        return main;
    }

    // =========================================================
    // DASHBOARD SUMMARY CARD
    // =========================================================

    private JPanel createSummaryCard(
            String title,
            int value,
            String icon) {

        JPanel card = new JPanel(
                new BorderLayout(10, 0)
        );
        card.setBackground(Color.WHITE);
        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(226, 232, 240)
                        ),
                        new EmptyBorder(
                                15, 17, 15, 17
                        )
                )
        );

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(
                new Font(
                        "Segoe UI Symbol",
                        Font.PLAIN,
                        28
                )
        );
        iconLabel.setForeground(blue);

        JPanel info = new JPanel();
        info.setBackground(Color.WHITE);
        info.setLayout(
                new BoxLayout(
                        info,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 12)
        );
        titleLabel.setForeground(
                new Color(100, 116, 139)
        );

        JLabel valueLabel = new JLabel(
                String.valueOf(value)
        );
        valueLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );
        valueLabel.setForeground(darkBlue);

        info.add(titleLabel);
        info.add(Box.createVerticalStrut(3));
        info.add(valueLabel);

        card.add(iconLabel, BorderLayout.WEST);
        card.add(info, BorderLayout.CENTER);

        return card;
    }

    // =========================================================
    // DASHBOARD TILE
    // =========================================================

    private JPanel createDashboardTile(
            String icon,
            String title,
            String description,
            java.awt.event.ActionListener action) {

        JPanel tile = new JPanel(
                new BorderLayout(16, 0)
        );
        tile.setBackground(Color.WHITE);
        tile.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );
        tile.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(226, 232, 240)
                        ),
                        new EmptyBorder(
                                20, 20, 20, 20
                        )
                )
        );

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(
                new Font(
                        "Segoe UI Symbol",
                        Font.PLAIN,
                        34
                )
        );
        iconLabel.setForeground(new Color(37, 99, 235));
        iconLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );
        iconLabel.setPreferredSize(
                new Dimension(55, 55)
        );

        JPanel info = new JPanel();
        info.setBackground(Color.WHITE);
        info.setLayout(
                new BoxLayout(
                        info,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 18)
        );
        titleLabel.setForeground(darkBlue);

        JLabel descLabel = new JLabel(
                "<html>" + description + "</html>"
        );
        descLabel.setFont(
                new Font("Arial", Font.PLAIN, 12)
        );
        descLabel.setForeground(
                new Color(100, 116, 139)
        );

        info.add(titleLabel);
        info.add(Box.createVerticalStrut(7));
        info.add(descLabel);

        JLabel arrow = new JLabel("›");
        arrow.setFont(
                new Font("Arial", Font.BOLD, 28)
        );
        arrow.setForeground(
                new Color(148, 163, 184)
        );

        tile.add(iconLabel, BorderLayout.WEST);
        tile.add(info, BorderLayout.CENTER);
        tile.add(arrow, BorderLayout.EAST);

        tile.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e) {
                        action.actionPerformed(
                                new java.awt.event.ActionEvent(
                                        tile,
                                        java.awt.event.ActionEvent.ACTION_PERFORMED,
                                        title
                                )
                        );
                    }

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {
                        tile.setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(
                                                new Color(
                                                        230,
                                                        76,
                                                        60
                                                ),
                                                1
                                        ),
                                        new EmptyBorder(
                                                20, 20, 20, 20
                                        )
                                )
                        );
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {
                        tile.setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(
                                                new Color(
                                                        230,
                                                        230,
                                                        230
                                                )
                                        ),
                                        new EmptyBorder(
                                                20, 20, 20, 20
                                        )
                                )
                        );
                    }
                }
        );

        return tile;
    }

    // =========================================================
    // PAGE
    // =========================================================

    private JPanel createPage() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        panel.setBackground(
                lightBackground
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(
                                0,
                                4,
                                0,
                                0,
                                blue
                        ),
                        new EmptyBorder(
                                25,
                                25,
                                25,
                                25
                        )
                )
        );

        return panel;
    }

    // =========================================================
    // HEADING
    // =========================================================

    private JLabel createHeading(
            String text) {

        JLabel heading =
                new JLabel(text);

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        heading.setForeground(
                darkBlue
        );

        return heading;
    }

    // =========================================================
    // ICON ACTION BUTTON
    // =========================================================

    private JButton createIconActionButton(
            String icon,
            String text) {

        JButton button =
                createActionButton(text);

        button.setLayout(
                new BorderLayout()
        );

        JLabel iconLabel =
                new JLabel(icon);

        iconLabel.setFont(
                new Font(
                        "Segoe UI Symbol",
                        Font.PLAIN,
                        30
                )
        );

        iconLabel.setForeground(
                Color.WHITE
        );

        iconLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        iconLabel.setPreferredSize(
                new Dimension(
                        55,
                        70
                )
        );

        JLabel textLabel =
                new JLabel(text);

        textLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        textLabel.setForeground(
                Color.WHITE
        );

        textLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        button.removeAll();

        button.add(
                iconLabel,
                BorderLayout.WEST
        );

        button.add(
                textLabel,
                BorderLayout.CENTER
        );

        return button;
    }

    // =========================================================
    // ACTION BUTTON
    // =========================================================

    private JButton createActionButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        button.setBackground(
                green
        );

        button.setForeground(
                Color.WHITE
        );

        button.setFocusPainted(false);

        button.setOpaque(true);

        button.setContentAreaFilled(true);

        // Fixed size: every menu box will be the same size
        button.setPreferredSize(
                new Dimension(
                        320,
                        90
                )
        );

        button.setMinimumSize(
                new Dimension(
                        320,
                        90
                )
        );

        button.setMaximumSize(
                new Dimension(
                        320,
                        90
                )
        );

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        205,
                                        58,
                                        46
                                ),
                                1
                        ),
                        new EmptyBorder(
                                10,
                                15,
                                10,
                                15
                        )
                )
        );

        return button;
    }

    // =========================================================
    // FIND CUSTOMER
    // =========================================================

    private Customer findCustomer(
            int id) {

        for (Customer c : customers) {

            if (c.id == id) {

                return c;
            }
        }

        return null;
    }

    // =========================================================
    // FIND RESTAURANT
    // =========================================================

    private Restaurant findRestaurant(
            int id) {

        for (Restaurant r : restaurants) {

            if (r.id == id) {

                return r;
            }
        }

        return null;
    }

    // =========================================================
    // FIND FOOD
    // =========================================================

    private Food findFood(
            int id) {

        for (Food f : foods) {

            if (f.id == id) {

                return f;
            }
        }

        return null;
    }

    // =========================================================
    // FIND ORDER
    // =========================================================

    private Order findOrder(
            int id) {

        for (Order o : orders) {

            if (o.id == id) {

                return o;
            }
        }

        return null;
    }

    // =========================================================
    // REFRESH
    // =========================================================

    private void refresh() {

        contentPanel.revalidate();

        contentPanel.repaint();
    }

    // =========================================================
    // CUSTOMER CLASS
    // =========================================================

    static class Customer {

        int id;
        String name;
        String location;
        String username;
        String password;

        Customer(
                int id,
                String name,
                String location,
                String username,
                String password) {

            this.id = id;
            this.name = name;
            this.location = location;
            this.username = username;
            this.password = password;
        }
    }

    // =========================================================
    // RESTAURANT CLASS
    // =========================================================

    static class Restaurant {

        int id;
        String name;
        String location;

        Restaurant(
                int id,
                String name,
                String location) {

            this.id = id;
            this.name = name;
            this.location = location;
        }
    }

    // =========================================================
    // FOOD CLASS
    // =========================================================

    static class Food {

        int id;
        String name;
        double price;
        int restaurantId;

        Food(
                int id,
                String name,
                double price,
                int restaurantId) {

            this.id = id;
            this.name = name;
            this.price = price;
            this.restaurantId = restaurantId;
        }
    }

    // =========================================================
    // CART ITEM CLASS
    // =========================================================

    static class CartItem {

        Food food;

        CartItem(Food food) {

            this.food = food;
        }
    }

    // =========================================================
    // ORDER CLASS
    // =========================================================

    static class Order {

        int id;
        int customerId;
        int restaurantId;
        int foodId;
        double amount;
        String status;
        String paymentMethod;
        String priority;
        int deliveryPartnerId;

        Order(
                int id,
                int customerId,
                int restaurantId,
                int foodId,
                double amount,
                String status,
                String paymentMethod,
                String priority,
                int deliveryPartnerId) {

            this.id = id;
            this.customerId = customerId;
            this.restaurantId = restaurantId;
            this.foodId = foodId;
            this.amount = amount;
            this.status = status;
            this.paymentMethod = paymentMethod;
            this.priority = priority;
            this.deliveryPartnerId = deliveryPartnerId;
        }
    }

    // =========================================================
    // DELIVERY PARTNER CLASS
    // =========================================================

    static class DeliveryPartner {

        int id;
        String name;
        String location;
        String username;
        String password;

        DeliveryPartner(
                int id,
                String name,
                String location,
                String username,
                String password) {

            this.id = id;
            this.name = name;
            this.location = location;
            this.username = username;
            this.password = password;
        }
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    Main main =
                            new Main();

                    main.setVisible(true);
                }
        );
    }
}