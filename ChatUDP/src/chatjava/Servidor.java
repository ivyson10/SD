/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatjava;

import java.net.*;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Servidor{

    private static HashSet<InetAddress> clientes;
    private static DatagramSocket server;
    private String nome;
    private static int porta = 12345;



    /**
     * *
     * Método main
     *
     * @param args
     */
    public static void main(String[] args) {

        try {
            //Cria os objetos necessário para instânciar o servidor
            JLabel lblMessage = new JLabel("Porta do Servidor:");
            JTextField txtPorta = new JTextField("12345");
            Object[] texts = {lblMessage, txtPorta};
            JOptionPane.showMessageDialog(null, texts);
            server = new DatagramSocket(Integer.parseInt(txtPorta.getText()));
            clientes = new HashSet<>();
            JOptionPane.showMessageDialog(null, "Servidor ativo na porta: "
                    + txtPorta.getText());

            while (true) {
                System.out.println("Aguardando pacotes...");
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                server.receive(receivePacket);
                /*boolean exsist = false;
                for (InetAddress cliente : clientes) {
                    
                    if(receivePacket.getAddress() == cliente){
                        
                        exsist = true;
                        break;
                        
                    }
                    
                }*/
                //if(exsist == false){
                    
                    clientes.add(receivePacket.getAddress());
                    
                //}
                String msg = new String(receivePacket.getData());
                
                System.out.println("Cliente " + receivePacket.getAddress() + " enviando pacotes...\nDados: " + msg);
                for(InetAddress cliente : clientes){
                    
                    if(receivePacket.getAddress() != cliente){
                        
                        DatagramPacket reply = new DatagramPacket(receivePacket.getData(), receivePacket.getData().length, cliente, porta);
                        server.send(reply);
                        
                    }
                    
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }finally {if(server != null) server.close();}
    }// Fim do método main                      
} //Fim da classe 


