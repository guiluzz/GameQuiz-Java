import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Quiz implements ActionListener{
	// Array para armazenar as perguntas
	String[] perguntas = {
		"Uma das formas de colaborar com a preservação do meio ambiente é reduzir a produção de resíduos. Mas como?",
		"Qual dos elementos abaixo não é utilizado como fonte de energia?",
		"Como preservar árvores e florestas?",
		"O que fazer com o lixo eletrônico – pilhas, baterias e equipamentos quebrados?"
	};
	// Array para armanezar as alternativas
	String[][] alternativas = {
			{"Optando pela compra de produtos com embalagens recicláveis.", "Reutilizando os materiais e objetos sempre que possível.", 
				"Apoiando iniciativas de reciclagem.", "Todas as anteriores."},
			{"Água corrente", "Petróleo", "Barra de ferro", "Sol"},
			{"Construindo uma casa na árvore.", "Reciclando papéis, jornais e revistas.", "Reutilizando metais e vidros.", "Indo em parques."},
			{"Recolher, organizar e armazenar em casa o máximo de tempo que der.", "Juntar com plásticos e metais.", 
				"Jogar no lixo comum.", "Procurar locais específicos para seu descarte."}
	};
	
	// Array para armazenar alternativas corretas
	char[] respostas = {
			'A',
			'C',
			'B',
			'A'
	};
	
	// Variaves globais 
	char chute;
	int index;
	int chutes_acertados = 0;
	int total_perguntas = perguntas.length;
	int resultado;
	int cronometro = 10;
	
	JFrame frame = new JFrame();
	JTextField cabecalho = new JTextField();
	JTextArea titulo_pergunta = new JTextArea();
	JButton botaoA = new JButton();
	JButton botaoB = new JButton();
	JButton botaoC = new JButton();
	JButton botaoD = new JButton();
	JLabel alternativa_A = new JLabel();
	JLabel alternativa_B = new JLabel();
	JLabel alternativa_C = new JLabel();
	JLabel alternativa_D = new JLabel();
	JLabel segundos = new JLabel();
	JTextField numero_acertos = new JTextField();
	JTextField porcentagem = new JTextField();
	
	// método para o cronometro de 10 segundos
	Timer timer = new Timer(1000, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			cronometro--;
			segundos.setText(String.valueOf(cronometro));
			if(cronometro<=0) {
				mostraResposta();
			}
		}
	});
	
	//Constructor construir toda a interface
	public Quiz() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200,1200);
		frame.getContentPane().setBackground(new Color(191,201,202));
		frame.setLayout(null);
		frame.setResizable(false);
		
		cabecalho.setBounds(0,0,1200,50);
		cabecalho.setBackground(new Color(191,201,202));
		cabecalho.setForeground(new Color(23,32,42));
		cabecalho.setFont(new Font("Arial", Font.BOLD, 30));
		cabecalho.setBorder(BorderFactory.createBevelBorder(1));
		cabecalho.setHorizontalAlignment(JTextField.CENTER);
		cabecalho.setEditable(false);
		
		titulo_pergunta.setBounds(0,50,1200,75);
		titulo_pergunta.setLineWrap(true);
		titulo_pergunta.setWrapStyleWord(true);
		titulo_pergunta.setBackground(new Color(191,201,202));
		titulo_pergunta.setForeground(new Color(23,32,42));
		titulo_pergunta.setFont(new Font("Arial", Font.BOLD, 25));
		titulo_pergunta.setBorder(BorderFactory.createBevelBorder(1));
		titulo_pergunta.setEditable(false);
		
		botaoA.setBounds(0,150,100,100);
		botaoA.setFont(new Font("Arial", Font.BOLD, 35));
		botaoA.setFocusable(false);
		botaoA.addActionListener(this);
		botaoA.setText("A");

		botaoB.setBounds(0,250,100,100);
		botaoB.setFont(new Font("Arial", Font.BOLD, 35));
		botaoB.setFocusable(false);
		botaoB.addActionListener(this);
		botaoB.setText("B");
		
		botaoC.setBounds(0,350,100,100);
		botaoC.setFont(new Font("Arial", Font.BOLD, 35));
		botaoC.setFocusable(false);
		botaoC.addActionListener(this);
		botaoC.setText("C");
		
		botaoD.setBounds(0,450,100,100);
		botaoD.setFont(new Font("Arial", Font.BOLD, 35));
		botaoD.setFocusable(false);
		botaoD.addActionListener(this);
		botaoD.setText("D");
		
		alternativa_A.setBounds(108,150,1200,100);
		alternativa_A.setBackground(new Color(191,201,202));
		alternativa_A.setForeground(new Color(23,32,42));
		alternativa_A.setFont(new Font("Arial",Font.PLAIN,35));
		
		alternativa_B.setBounds(108,250,1200,100);
		alternativa_B.setBackground(new Color(191,201,202));
		alternativa_B.setForeground(new Color(23,32,42));
		alternativa_B.setFont(new Font("Arial",Font.PLAIN,35));

		alternativa_C.setBounds(108,350,1200,100);
		alternativa_C.setBackground(new Color(191,201,202));
		alternativa_C.setForeground(new Color(23,32,42));
		alternativa_C.setFont(new Font("Arial",Font.PLAIN,35));
			
		alternativa_D.setBounds(108,450,1200,100);
		alternativa_D.setBackground(new Color(191,201,202));
		alternativa_D.setForeground(new Color(23,32,42));
		alternativa_D.setFont(new Font("Arial",Font.PLAIN,35));

		segundos.setBounds(0,560,1200,175);
		segundos.setBackground(new Color(25,25,25));
		segundos.setForeground(new Color(255,0,0));
		segundos.setFont(new Font("Arial",Font.BOLD, 60));
		segundos.setOpaque(true);
		segundos.setHorizontalAlignment(JTextField.CENTER);
		segundos.setText(String.valueOf(cronometro));
		
		numero_acertos.setBounds(490,225,200,100);
		numero_acertos.setBackground(new Color(25,25,25));
		numero_acertos.setForeground(new Color(25,255,0));
		numero_acertos.setFont(new Font("Arial", Font.BOLD, 50));
		numero_acertos.setBorder(BorderFactory.createBevelBorder(1));
		numero_acertos.setHorizontalAlignment(JTextField.CENTER);
		numero_acertos.setEditable(false);
		
		porcentagem.setBounds(490,325,200,100);
		porcentagem.setBackground(new Color(25,25,25));
		porcentagem.setForeground(new Color(25,255,0));
		porcentagem.setFont(new Font("Arial", Font.BOLD, 50));
		porcentagem.setBorder(BorderFactory.createBevelBorder(1));
		porcentagem.setHorizontalAlignment(JTextField.CENTER);
		porcentagem.setEditable(false);
		

		frame.add(segundos);
		frame.add(alternativa_A);
		frame.add(alternativa_B);
		frame.add(alternativa_C);
		frame.add(alternativa_D);
		frame.add(botaoA);
		frame.add(botaoB);
		frame.add(botaoC);
		frame.add(botaoD);
		frame.add(titulo_pergunta);
		frame.add(cabecalho);
		frame.setVisible(true);
		
		proximaPergunta();
	}
	//Método para proxima pergunta
	public void proximaPergunta() {
		if(index>=total_perguntas) {
			mostraResultado();
		}
		else {
			cabecalho.setText("Questão " +(index+1)); //Defini em qual pergunta estamos
			titulo_pergunta.setText(perguntas[index]);  //Defini o título da pergunta 
			alternativa_A.setText(alternativas[index][0]); // Defini a alternativa
			alternativa_B.setText(alternativas[index][1]); 
			alternativa_C.setText(alternativas[index][2]); 
			alternativa_D.setText(alternativas[index][3]); 
			timer.start();
		}
	}
	//Método para disparar quando a ação for executada
	@Override
	public void actionPerformed(ActionEvent e) {
		botaoA.setEnabled(false);
		botaoB.setEnabled(false);
		botaoC.setEnabled(false);
		botaoD.setEnabled(false);
		
		// Verifica se botão que foi clicado corresponde com a respota correta, se sim incrementa os acertos
		if(e.getSource()==botaoA) {
			chute = 'A';
			if(chute == respostas[index]) {
				chutes_acertados++;
			}
		}
		if(e.getSource()==botaoB) {
			chute = 'B';
			if(chute == respostas[index]) {
				chutes_acertados++;
			}
		}
		if(e.getSource()==botaoC) {
			chute = 'C';
			if(chute == respostas[index]) {
				chutes_acertados++;
			}
		}
		if(e.getSource()==botaoD) {
			chute = 'D';
			if(chute == respostas[index]) {
				chutes_acertados++;
			}
		}
		mostraResposta();
		
	}
	//Método para mostrar a resposta
	public void mostraResposta() {
		timer.stop();
		
		botaoA.setEnabled(false);
		botaoB.setEnabled(false);
		botaoC.setEnabled(false);
		botaoD.setEnabled(false);
		
		// destacar a alternativa incorreta em vermelho
		if(respostas[index] != 'A')
			alternativa_A.setForeground(new Color(255,0,0));
		if(respostas[index] != 'B')
			alternativa_B.setForeground(new Color(255,0,0));
		if(respostas[index] != 'C')
			alternativa_C.setForeground(new Color(255,0,0));
		if(respostas[index] != 'D')
			alternativa_D.setForeground(new Color(255,0,0));
		
		Timer pause = new Timer(2000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				alternativa_A.setForeground(new Color(23,32,42));
				alternativa_B.setForeground(new Color(23,32,42));
				alternativa_C.setForeground(new Color(23,32,42));
				alternativa_D.setForeground(new Color(23,32,42));
				
				chute = ' ';
				cronometro=10;
				segundos.setText(String.valueOf(cronometro));
				botaoA.setEnabled(true);
				botaoB.setEnabled(true);
				botaoC.setEnabled(true);
				botaoD.setEnabled(true);
				index++;
				proximaPergunta();
			}
		});
		pause.setRepeats(false);
		pause.start();
	}
	//Método para mostrar o resultado
	public void mostraResultado() {
		botaoA.setEnabled(false);
		botaoB.setEnabled(false);
		botaoC.setEnabled(false);
		botaoD.setEnabled(false);
		
		resultado = (int)((chutes_acertados/(double)total_perguntas)*100);
		
		cabecalho.setText("Resultado!");
		titulo_pergunta.setText("");
		alternativa_A.setText("");
		alternativa_B.setText("");
		alternativa_C.setText("");
		alternativa_D.setText("");
		
		numero_acertos.setText("("+chutes_acertados+"/"+total_perguntas+")");
		porcentagem.setText(resultado+"%");
		
		frame.add(numero_acertos);
		frame.add(porcentagem);
	}
}
