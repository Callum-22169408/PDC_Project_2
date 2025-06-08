package pdc_project_2;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CardPanel extends JPanel {
    private List<Card> hand;
    private static final int CARD_WIDTH = 80;
    private static final int CARD_HEIGHT = 120;

    public CardPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
        refresh();
    }

    public void refresh() {
        this.removeAll();
        if (hand != null) {
            for (Card card : hand) {
                String resourcePath = card.getImageResourcePath();
                java.net.URL imgURL = getClass().getResource(resourcePath);
                if (imgURL != null) {
                    ImageIcon originalIcon = new ImageIcon(imgURL);
                    Image scaledImage = originalIcon.getImage().getScaledInstance(
                        CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH
                    );
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    JLabel label = new JLabel(scaledIcon);
                    label.setToolTipText(card.toString());
                    this.add(label);
                } else {
                    JLabel label = new JLabel(card.toString());
                    this.add(label);
                }
            }
        }
        revalidate();
        repaint();
    }
}