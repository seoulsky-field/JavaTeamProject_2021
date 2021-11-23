import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public void main_gui() {
        setSize(400, 400);
        setTitle("백신");

        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        JButton BTN_group_add = new JButton();
        BTN_group_add.setLocation(25, 20);
        BTN_group_add.setSize(325, 30);
        BTN_group_add.setText("그룹 추가");
        BTN_group_add.setFont(new Font("gothic", Font.BOLD, 20));
        BTN_group_add.setBackground(Color.blue);
        BTN_group_add.setOpaque(true);

        contentPane.add(BTN_group_add);

        JButton BTN_group_show = new JButton();
        BTN_group_show.setLocation(25, 70);
        BTN_group_show.setSize(150, 120);
        BTN_group_show.setText("그룹 정보 조회");
        BTN_group_show.setFont(new Font("gothic", Font.BOLD, 20));
        BTN_group_show.setBackground(Color.blue);
        BTN_group_show.setOpaque(true);

        contentPane.add(BTN_group_show);

        JButton BTN_group_modify = new JButton();
        BTN_group_modify.setLocation(200, 70);
        BTN_group_modify.setSize(150, 120);
        BTN_group_modify.setText("그룹 정보 수정");
        BTN_group_modify.setBackground(Color.blue);
        BTN_group_modify.setOpaque(true);

        contentPane.add(BTN_group_modify);

        JButton BTN_group_delete = new JButton();
        BTN_group_delete.setLocation(25, 220);
        BTN_group_delete.setSize(150, 120);
        BTN_group_delete.setText("그룹 삭제");
        BTN_group_delete.setBackground(Color.blue);
        BTN_group_delete.setOpaque(true);

        contentPane.add(BTN_group_delete);

        JButton BTN_report_print = new JButton();
        BTN_report_print.setLocation(200, 220);
        BTN_report_print.setSize(150, 120);
        BTN_report_print.setText("최종 보고서 출력");
        BTN_report_print.setBackground(Color.blue);
        BTN_report_print.setOpaque(true);

        contentPane.add(BTN_report_print);




        setVisible(true);
    }

    // 그룹 추가를 담당하는 함수
    public void group_add() {

    }

    // 그룹 수정을 담당하는 함수
    public void group_modify() {

    }

    // 그룹 수정을 담당하는 함수
    public void group_delete() {

    }

    public void report_print() {

    }

}
