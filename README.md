# Função Lambda AWS - lambda-fastfood
## Visão Geral

Função lambda para se autenticar no AWS Cognito.

## Principais Tecnologias Utilizadas

- **Java**
- **AWS Lambda**
- **Cognito**
- **Terraform**
- **Git Actions**
- **Maven**

## Iniciando

Enviar modificações para a branch main requer:

- Um PR aprovado (por membros do time e alguns serviços automatizados);
No merging todas as mudanças serão automaticamente integradas pelo Github Actions.

## Como Executar 
Para executar o sistema, siga as instruções abaixo:

### Clonando o Repositório e Criando infraestrutura

1. Clone o repositório executando o comando:  
`git clone https://github.com/SaraAWatanabe-org/lambda-fastfood.git`

3. Entre na pasta do projeto:  
`cd lambda-fastfood/terraform`

4. Configure a propriedade 'client_id' no arquivo 'variables.tfvars' para apontar para o client_id de seu recurso no AWS Cognito.

5. Certifique-se que suas credenciais AWS esteja configuradas corretamente. [Mais Informações](https://registry.terraform.io/providers/hashicorp/aws/latest/docs)

6. Execute o comando para baixar as dependências necessárias:
`terraform init`

7. Execute o comando e depois confirme digitando "yes" para criar a infraestrutura:
`terraform apply -var-file="variables.tfvars" `

8. Após infraestrutura criada, você pode configurar um enviroment para o git actions ou subir o .jar manualmente na AWS.

9. Para poder executar sua função lambda, basta vincular um trigger a um Api Gateway na AWS.

10. Utilize o seguinte comando para apagar a infraestrutura na AWS se necessário:
`terraform destroy -var-file="variables.tfvars" `
