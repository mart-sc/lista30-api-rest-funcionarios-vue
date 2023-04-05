# Lista 30 - Front-end API REST (Vue.js) e Back-end API REST (STS)
- Desenvolvido no curso DEV-TI (Unoesc)

## Como rodar o Back-end:
> **Warning**: É importante que o back-end rode antes, caso contrário, pode acontecer do front iniciar na porta 8080, que já está configurada para o servidor do back-end
1. Tenha o STS (Spring Tool Suit 4) instalado.
2. Importe o back-end utilizando o menu "File > Import > Projects from folder or Archives"
3. Com o botão direito na classe principal ou na pasta do projeto, clique em "Run As > Spring Boot Application"
4. Você pode acessar localhost:8080/h2-console, e apenas alterar a url para: jdbc:h2:mem:h2-funcionarios, assim, é possível fazer consultas de testes utilizando o banco em memória
5. Com o servidor do backend rodando na porta 8080, você pode acessar a API REST de funcionários em: localhost:8080/api/funcionarios

## Como rodar o Front-end:
1. Tenha o VS Code instalado, abra a pasta do front no VS code (File > Open Folder > front_end_funcionarios)
2. Execute os seguintes comandos no terminal (Ctrl + '):

```
npm install
```

```
npm run serve
```

```
npm run build
```

```
npm run lint
```

3. Feito isso, o aplicativo do front-end deve iniciar na porta 8081, para acessar o front acesse: localhost:8081
