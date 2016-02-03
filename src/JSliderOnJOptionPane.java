import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JSliderOnJOptionPane {
  
  static JSlider getSlider(final JOptionPane optionPane) {
    JSlider slider = new JSlider();
    slider.setMajorTickSpacing(10);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    ChangeListener changeListener = new ChangeListener() {
      public void stateChanged(ChangeEvent changeEvent) {
        JSlider theSlider = (JSlider) changeEvent.getSource();
        if (!theSlider.getValueIsAdjusting()) {
          optionPane.setInputValue(new Integer(theSlider.getValue()));
        }
      }
    };
    slider.addChangeListener(changeListener);
    return slider;
  }

}