# Chat Client / Servidor - Implementando uma aplicação de Redes

Apresentamos a seguinte solução do trabalho "Chat Cliente / Servidor" da disciplina MATA59 - REDES DE COMPUTADORES I: a implementação de um sistema que permite a comunicação de diversos clientes através de um servidor central com o uso da linguagem Java compatível com o sistema operacional Linux.

## Iniciando

As seguintes instruções permitirá a execução do nosso sistema, utilizando a linguagem Java (versão 11), e o Terminal Ubuntu.

### Pré-requisitos

Para rodar o sistema, você precisará ter instalado no seu sistema os componentes

```
Java - versão 11
Terminal Ubuntu
```

e os arquivos

```
executavel/server_chat.jar
executavel/client_chat.jar
```

### Instalação do Java

Para evitar erros de compatibilidade ao executar os arquivos, cheque a versão do Java no seu computador através do comando

```
java -version
```

Caso não possua a versão 11 do Java, você poderá executar os seguintes comandos em sequência:

```
$ sudo apt install openjdk-11-jdk
$ sudo update-alternatives --config java

- ao exibir as opções, escolha Java 11 
```

Para confirmar se houve a instalação e seleção da versão 11 do Java, execute novamente o comando

```
java -version
```

Se ainda não for a versão requerida, execute o comando

```
/usr/bin/java -jar
```

para rodar os arquivos em formato .jar na pasta "executavel"


## Execução 

Para executar o programa do servidor da aplicação, acesse a pasta "executavel" do repositório e execute o comando:

```
java -jar server_chat.jar <PORT>
```

recebendo como único argumento o número da porta (<PORT>) na qual aguardará os clientes se conectarem.

Recebendo um número da porta válido, o servidor irá aguardas as futuras conexões de clientes na porta configurada. Esse servidor possibilitará a comunicação entre todos os clientes.

Para executar o programa do cliente, na pasta "executavel" do repositório execute o comando:
```
java -jar client_chat.jar  <CLIENT_NAME> <SERVER_ADDRESS> <PORT>
```

recebendo como argumentos o nome do cliente, o endereço do servidor e o número da porta configurado no servidor.

### Comandos para o programa

* Para enviar uma mensagem para todos os clientes de um cliente, através do Terminal digite
```
SEND <MESSAGEM> 
```
sendo o SEND o comando e <MENSAGEM> o único argumento da mensagem


* Para enviar uma mensagem para um cliente de um cliente, através do Terminal digite
```
SENDTO <NOME_DO_CLIENTE> <MENSAGEM> 
```
sendo o SENDTO o comando, <NOME_DO_CLIENTE> o primeiro argumento para o nome do cliente que deseja enviar e <MENSAGEM> o segundo argumento para a mensagem


* Para retornar a lista dos clientes conectados ao servidor, através do Terminal digite
```
WHO
```
sendo o WHO o comando


* Para retornar a lista dos comandos, através do Terminal digite
```
HELP
```
sendo o HELP o comando


* Para finalizar a sessão do cliente ou do servidor, através do Terminal digite o comando 

```
Ctrl-C
```
no Terminal respectivo.

Sendo o Terminal do programa do cliente, ele se desconectará do servidor e terminará. Caso seja do servidor, ele desconectará de todos os clientes e finalizará o programa.

## Autores

* **ANDREI DA SILVA MACEDO** - *andrei.silva@ufba.br*
* **IAGO PINTO DO ESPIRITO SANTO** - *iago.espirito@ufba.br*
* **SIDNEI SANTIAGO SOARES** - *sidnei.santiago@ufba.br*
* **VINICIUS BRITO DE JESUS** - *viniciusbj@ufba.br*
