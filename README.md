# Instruções para execução do serviço
Para executar e subir a aplicação em um ambiente Unix, o script start.sh localizado na raiz do projeto deve ser executado, para isso:

1. Tornar o script executável através do comando: **chmod +x start.sh**
1. Executar o script através do comando: **./start.sh**
	1. (Opcional): Informar os valores para GOOGLE_CLIENT_ID e GOOGLE_CLIENT_SECRET para que seja possível o login/autenticação com o Google. Para obter este valores é necessário criar um projeto no Google Cloud([documentação - Criar um projeto](https://developers.google.com/maps/documentation/android-sdk/cloud-setup?hl=pt-br#create)) e após isso criar o ID do cliente OAuth([documentação](https://support.google.com/workspacemigrate/answer/9222992?hl=pt-BR))
	

Obs.: por padrão foi utlizado o banco de dados H2 que roda em memória, e para o mesmo não é necessário nenhuma configuração adicional.

O serviço estará disponível em **http://localhost:8080** e para acessá-lo temos as seguintes opções:

1. Criar um novo usuário, e após utilizar os dados(usuario e senha) para fazer o login.
1. Se autenticar usando uma conta Google(**Importante: Irá funcionar somente se foi seguido o passo **)


# Descrição do projeto
Você recebeu um arquivo de texto com os dados de vendas da empresa. Precisamos criar uma maneira para que estes dados sejam importados para um banco de dados.

Sua tarefa é criar uma interface web que aceite upload de arquivos, normalize os dados e armazene-os em um banco de dados relacional.

Sua aplicação web DEVE:

1. Aceitar (via um formulário) o upload de arquivos separados por TAB com as seguintes colunas: purchaser name, item description, item price, purchase count, merchant address, merchant name. Você pode assumir que as colunas estarão sempre nesta ordem, que sempre haverá dados em cada coluna, e que sempre haverá uma linha de cabeçalho. Um arquivo de exemplo chamado example_input.tab está incluído neste repositório.
1. Interpretar ("parsear") o arquivo recebido, normalizar os dados, e salvar corretamente a informação em um banco de dados relacional.
1. Exibir a receita bruta total representada pelo arquivo enviado após o upload + parser.
1. Ser escrita obrigatoriamente em Java com Spring Boot (ou outra linguagem especificada para a vaga se for o caso).
1. Ser simples de configurar e rodar, funcionando em ambiente compatível com Unix (Linux ou Mac OS X). Ela deve utilizar apenas linguagens e bibliotecas livres ou gratuitas.