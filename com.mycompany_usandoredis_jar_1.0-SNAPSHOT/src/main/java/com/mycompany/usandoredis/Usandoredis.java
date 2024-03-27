
package com.mycompany.usandoredis;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.Connection;
import java.util.Scanner;
import java.util.Set;
/**
 *
 * @author lunam
 */
public class Usandoredis {

    public static void main(String[] args) {
                        Jedis jedis = new Jedis("redis://default:6rb7RAJ6K4GJkGfZILHOpL0pxfM4t32K@redis-14880.c308.sa-east-1-1.ec2.cloud.redislabs.com:14880");
        Connection connection = jedis.getConnection();
        Scanner scan = new Scanner(System.in);

        connection.connect();

        System.out.println("Adicionar uma tarefa: ");
        System.out.println("Digite o numero da tarefa: ");
        String id = scan.nextLine();
        System.out.println("Digite a tarefa: ");
        String Tarefa = scan.nextLine();
        System.out.println("Digite o status");
        String Status = scan.nextLine();

        String chaveTarefa = "Tarefa:" + id;
        String tarefaInfo = "{Tarefa:" + id + " Tarefa:'" + Tarefa + "' ,Descricao:'" + Status + "',id:'" + id + "'}";
        jedis.set(chaveTarefa, tarefaInfo);
        System.out.println(jedis.get(chaveTarefa));

        System.out.println("Se deseja atualizar o status da tarefa digite o valor:1 | Caso o contrário o valor: 2 ");
        String escolha = scan.nextLine();

        switch (escolha) {
            case "1":
                System.out.println("Digite o número da tarefa que deseja atualizar:");
                String numeroTarefaAtualizar = scan.nextLine();
                System.out.println("Digite o novo status para a tarefa: ");
                Status = scan.nextLine();
                String chaveTarefaAtualizar = "Tarefa:" + numeroTarefaAtualizar;
                String tarefaInfoAtualizar = "{Tarefa:" + numeroTarefaAtualizar + " Tarefa:'" + Tarefa + "' ,Descricao:'" + Status + "',id:'" + id + "'}";
                jedis.set(chaveTarefaAtualizar, tarefaInfoAtualizar);
                System.out.println(jedis.get(chaveTarefaAtualizar));
                System.out.println("Status atualizado com sucesso!");
                break;
            case "2":
                System.out.println("O que você deseja?");
                System.out.println("Listar as tarefas: 1");
                System.out.println("Deletar uma tarefa: 2");

                int opcao = scan.nextInt();
                if (opcao == 1) {
                    System.out.println(jedis.keys("Tarefa:*"));
                } else if (opcao == 2) {
                    System.out.println("Digite o número da tarefa que deseja deletar:");
                    String numeroTarefaDeletar = scan.next();
                    String chaveTarefaDeletar = "Tarefa:" + numeroTarefaDeletar;
                    System.out.println(jedis.get(chaveTarefaDeletar));
                    jedis.del(chaveTarefaDeletar);
                    System.out.println("Tarefa deletada com sucesso.");
                }
                break;
            default:
                System.out.println("Opção não encontrada");
                break;
        }

        scan.close();
        }
    }

