# clinical-consult-api
Uma API que segue os padrôes REST. Onde é possível realizar, cadastro, listagem, atualizar e deletar
um médico ou paciente. Para realizar as funcionalidades do crud vai ser necessário a criação de um 
usuário para a gereção de um token JWT. 

# Criação de usuário e geração de token

    - Para a criação do usuário utilizar o endpoint "/users"
    - Após a criação do usuário, gerar o token no endpoint "/auth"

# Ferramentas

    - Java 18
    - Spring boot
    - Mysql
    - Junit 5

# Usuário
       • email: admin@email.com
       • password: 12345

# Executando o projeto localmente

	• Swagger http://localhost:8080/v2/api-docs