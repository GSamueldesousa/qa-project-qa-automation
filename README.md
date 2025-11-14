🧪 Automação de Testes – Login System (Java + Selenium)

Este repositório contém o projeto automatizado desenvolvido para validar o fluxo de Login de um sistema com perfis ADMIN, USER e VISITOR.

O objetivo deste README é mostrar como executar o projeto, desde a instalação até a execução final dos testes.


---

🚀 1. Pré-Requisitos

Antes de executar o projeto, certifique-se de ter instalado:

✔️ Java 17 ou superior

Para verificar:

java -version

✔️ Maven 3.8+

Para verificar:

mvn -version

✔️ Google Chrome instalado

O WebDriver é configurado automaticamente via WebDriverManager.

✔️ Git instalado

Para clonar o repositório.


---

📥 2. Como Clonar o Projeto

git clone https://github.com/seu-usuario/nome-do-projeto.git
cd nome-do-projeto


---

📦 3. Instalar Dependências

Toda configuração do projeto está no arquivo pom.xml.

Execute:

mvn clean install

Esse comando irá:

Baixar dependências do Selenium

Baixar JUnit

Configurar WebDriverManager

Validar estrutura do projeto



---

▶️ 4. Como Executar os Testes

✅ Executar todos os testes automatizados

mvn test


---

🎯 5. Executar uma Classe de Teste Específica

mvn -Dtest=LoginTest test

Exemplo usando outra classe:

mvn -Dtest=SecurityTests test


---

📊 6. Relatórios (se configurado no pom.xml)

Caso o Allure ou Surefire Reports esteja habilitado:

Ver relatório Surefire:

/target/surefire-reports/index.html

Abra no navegador.

Ver relatório Allure:

allure serve target/allure-results


---

🧱 7. Estrutura do Projeto (Resumo)

src
 ├── main
 │    └── java
 │         ├── core        # DriverFactory, BaseTest
 │         ├── pages       # Page Objects
 │         └── utils       # Controladores de dados
 └── test
      └── java
           ├── LoginTest.java
           └── SecurityTests.java


---

🔧 8. Configurações Importantes

🌐 Driver

O driver é instanciado automaticamente:

Baseado no navegador padrão (Chrome)

Sem necessidade de download manual

Gerenciado via WebDriverManager


📄 Massa de Teste

Massa está centralizada em:

src/main/java/.../utils/TestData.java


---

👨‍💻 9. Comandos Gerais de Desenvolvimento

Atualizar dependências

mvn clean install -U

Rodar em modo debug no IntelliJ/VSCode

Abra a classe de teste → botão Run Debug.


---

🙋‍♂️ 10. Autor

Samuel Ferreira de Sousa
Analista de Testes – Automação Web, API e Mobile
