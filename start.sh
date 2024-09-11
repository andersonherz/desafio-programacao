#!/bin/bash

echo "Iniciando a compilação e empacotamento do projeto..."

cd "$(dirname "$0")"

# Executar o Maven para limpar, compilar e empacotar o projeto
mvn clean package

# Verificar se o build foi bem-sucedido
if [ $? -eq 0 ]; then
    echo "Build e empacotamento bem-sucedidos."

    # Local do JAR gerado
    JAR_FILE=target/desafio-luizalabs-1.0.0.jar

    # Verificar se o arquivo JAR foi gerado corretamente
    if [ -f "$JAR_FILE" ]; then
        echo "Arquivo JAR encontrado: $JAR_FILE"
        
        # Solicitar ao usuário o valor das variáveis de ambiente
        read -p "Digite o GOOGLE_CLIENT_ID (ou deixe em branco para manter o valor atual): " input_client_id
        read -p "Digite o GOOGLE_CLIENT_SECRET (ou deixe em branco para manter o valor atual): " input_client_secret

        # Manter valores padrão ou usar os fornecidos pelo usuário
        GOOGLE_CLIENT_ID="${input_client_id:-$GOOGLE_CLIENT_ID}"
        GOOGLE_CLIENT_SECRET="${input_client_secret:-$GOOGLE_CLIENT_SECRET}"

        # Exportar as variáveis de ambiente para o processo atual
        export GOOGLE_CLIENT_ID
        export GOOGLE_CLIENT_SECRET

        echo "Executando a aplicação..."
        java -jar "$JAR_FILE"
    else
        echo "Erro: Arquivo JAR não encontrado no diretório target."
        exit 1
    fi
else
    echo "Erro durante o build do projeto. Verifique o log acima."
    exit 1
fi
