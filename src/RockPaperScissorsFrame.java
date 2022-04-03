import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    static Random rand = new Random();

    //Extra Credit Variables - R0, P1, S2
    static int lastUse = -1;
    static int[] moveUsage = {0, 0, 0};
    static int wins = 0;
    static int loss = 0;
    static int ties = 0;

    JPanel mainPnl;

    JPanel moveSelect;
    JButton rockBtn;
    ImageIcon rockIco;
    JButton paperBtn;
    ImageIcon paperIco;
    JButton scissorBtn;
    ImageIcon scissorIco;
    JButton quitBtn;

    JPanel statPnl;
    JLabel pWinLbl;
    JTextField pWinFld;
    JLabel cWinLbl;
    JTextField cWinFld;
    JLabel tieLbl;
    JTextField tieFld;

    JPanel resultsPnl;
    JTextArea textArea = new JTextArea(3,40);

    public RockPaperScissorsFrame(){
        setTitle("Rock Paper Scissors Game");

        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(3,1));

        createMoveSelect();
        mainPnl.add(moveSelect);
        createStatPnl();
        mainPnl.add(statPnl);
        createResultsPnl();
        mainPnl.add(resultsPnl);

        add(mainPnl);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createMoveSelect(){
        moveSelect = new JPanel();
        moveSelect.setLayout(new GridLayout(1, 4));
        moveSelect.setBorder(new TitledBorder(new EtchedBorder()));

        rockIco = new ImageIcon("src/Images/rock.png");
        rockIco = new ImageIcon(rockIco.getImage().getScaledInstance(75, 75, Image.SCALE_FAST));
        rockBtn = new JButton(rockIco);
        rockBtn.addActionListener(e -> {playMove(0);});

        paperIco = new ImageIcon("src/Images/paper.png");
        paperIco = new ImageIcon(paperIco.getImage().getScaledInstance(75, 75, Image.SCALE_FAST));
        paperBtn = new JButton(paperIco);
        paperBtn.addActionListener(e -> {playMove(1);});

        scissorIco = new ImageIcon("src/Images/scissor.png");
        scissorIco = new ImageIcon(scissorIco.getImage().getScaledInstance(75, 75, Image.SCALE_FAST));
        scissorBtn = new JButton(scissorIco);
        scissorBtn.addActionListener(e -> {playMove(2);});

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener(e -> {System.exit(0);});

        moveSelect.add(rockBtn);
        moveSelect.add(paperBtn);
        moveSelect.add(scissorBtn);
        moveSelect.add(quitBtn);
    }

    private void createStatPnl(){
        statPnl = new JPanel();
        statPnl.setLayout(new GridLayout(2, 3));
        statPnl.setBorder(new TitledBorder(new EtchedBorder()));

        pWinFld = new JTextField(String.valueOf(wins),10);
        cWinFld = new JTextField(String.valueOf(loss),10);
        tieFld = new JTextField(String.valueOf(ties),10);

        pWinLbl = new JLabel("Player Wins");
        cWinLbl = new JLabel("Computer Wins");
        tieLbl = new JLabel("Ties");

        statPnl.add(pWinFld);
        statPnl.add(cWinFld);
        statPnl.add(tieFld);
        statPnl.add(pWinLbl);
        statPnl.add(cWinLbl);
        statPnl.add(tieLbl);
    }

    private void createResultsPnl(){
        resultsPnl = new JPanel();
        JScrollPane scrollPane = new JScrollPane(textArea);
        resultsPnl.add(scrollPane, BorderLayout.CENTER);
    }

    private void playMove(int playerMove){
        switch (findWinner(playerMove, computerMove(playerMove))){
            case 0:
                ties++;
                break;
            case 1:
                wins++;
                break;
            case 2:
                loss++;
                break;
        }
        updateScore();
    }

    private void updateScore(){
        pWinFld.setText(String.valueOf(wins));
        cWinFld.setText(String.valueOf(loss));
        tieFld.setText(String.valueOf(ties));
    }

    //t-0, p1-1, p2-2
    private int findWinner(int p1, int p2){
        if(p1 == 0){
            if(p2 == 0){
                textArea.append("Rock Vs. Rock (Tie)\n");
                return 0;
            }
            else if(p2 == 1){
                textArea.append("Paper covers Rock (Computer Wins)\n");
                return 2;
            }
            else{
                textArea.append("Rock breaks Scissors (Player Wins)\n");
                return 1;
            }
        }
        else if(p1 == 1){
            if(p2 == 0){
                textArea.append("Paper covers Rock (Player Wins)\n");
                return 1;
            }
            else if(p2 == 1){
                textArea.append("Paper Vs. Paper (Tie)\n");
                return 0;
            }
            else{
                textArea.append("Scissors cuts Paper (Computer Wins)\n");
                return 2;
            }
        }
        else{
            if(p2 == 0){
                textArea.append("Rock breaks Scissors (Computer Wins)\n");
                return 2;
            }
            else if(p2 == 1){
                textArea.append("Scissors cuts Paper (Player Wins)\n");
                return 1;
            }
            else{
                textArea.append("Scissors Vs. Scissors (Tie)\n");
                return 0;
            }
        }
    }

    private static int computerMove(int playerMove){
        Strategy[] strats = new Strategy[]{new LeastStrategy(), new MostStrategy(), new LastStrategy(), new RandomStrategy()};
        Strategy cheatStrat = new CheatStrategy();
        if(rand.nextDouble() <= 0.1){
            return cheatStrat.determineMove(playerMove, lastUse, moveUsage);
        }
        else{
            return strats[rand.nextInt(strats.length)].determineMove(playerMove, lastUse, moveUsage);
        }
    }
}