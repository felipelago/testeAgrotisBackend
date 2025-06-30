<h1>Agrotis Backend API Test</h1>
<p></p>
<hr/>

<p>Uma API REST desenvolvida em Java com Spring Boot para gerenciamento de pessoas, laboratórios e propriedades rurais.</p>

<h2>💡 Tecnologias utilizadas</h2>

<h3>Java 21 + Spring Boot 3</h3>
<ul>
  <li><strong>Spring Boot</strong>: framework robusto e produtivo para aplicações Java modernas.</li>
  <li><strong>Spring Web</strong>: para criação de endpoints REST.</li>
  <li><strong>Spring Data JPA</strong>: para persistência de dados.</li>
  <li><strong>Validation</strong>: para validação de inputs.</li>
  <li><strong>H2 Database</strong>: banco de dados em memória para desenvolvimento.</li>
  <li><strong>SpringDoc OpenAPI (Swagger)</strong>: para documentação da API.</li>
  <li><strong>Maven</strong>: gerenciamento de dependências.</li>
</ul>

<h2>🏗️ Arquitetura</h2>

<h3>Padrão Arquitetural</h3>
<ul>
  <li><strong>Arquitetura em Camadas (Layered Architecture)</strong></li>
  <li><strong>Padrão MVC</strong> na camada de apresentação</li>
</ul>

<h3>Organização do Código</h3>
<p>O projeto utiliza <strong>organização por feature</strong> (<code>package-by-feature</code>) ao invés de <code>package-by-layer</code>, proporcionando:</p>

<h4>Principais Vantagens:</h4>

<ol>
  <li><strong>Coesão e Acoplamento</strong>
    <ul>
      <li><strong>Feature</strong>: Tudo sobre uma funcionalidade fica junto</li>
      <li><strong>Layer</strong>: Classes relacionadas ficam espalhadas em diferentes pacotes</li>
    </ul>
  </li>
  
  <li><strong>Navegação no Código</strong>
    <ul>
      <li><strong>Feature</strong>: Tudo sobre "Pessoa" está em um único lugar</li>
      <li><strong>Layer</strong>: Para entender "Pessoa", você navega entre 5 pastas diferentes</li>
    </ul>
  </li>
  
  <li><strong>Modificações e Manutenção</strong>
    <ul>
      <li>Mudanças em uma feature ficam isoladas em seu próprio pacote</li>
      <li>Menor impacto em outras funcionalidades</li>
    </ul>
  </li>
  
  <li><strong>Escalabilidade da Equipe</strong>
    <ul>
      <li><strong>Feature</strong>: Cada desenvolvedor pode trabalhar em sua feature com menos conflitos</li>
      <li><strong>Layer</strong>: Desenvolvedores trabalhando em features diferentes colidem nos mesmos pacotes</li>
    </ul>
  </li>
</ol>

<h3>Arquitetura Híbrida</h3>

<p>O projeto implementa uma <strong>arquitetura híbrida</strong> com separação clara de responsabilidades:</p>

<pre>
Feature/
├── Controller (Apresentação)
├── Service (Lógica de Negócio)
├── Adapter (Infraestrutura)
├── Repository (Persistência)
├── Model (Entidades)
└── DTOs (Request/Response)
</pre>

<h4>Vantagens desta Abordagem:</h4>

<ul>
  <li><strong>Service puro</strong>: Fácil de testar, sem dependências de infraestrutura</li>
  <li><strong>Adapter isolado</strong>: Mudanças no banco não afetam a lógica</li>
  <li><strong>Flexibilidade</strong>: Pode trocar implementações facilmente</li>
  <li><strong>Clareza</strong>: Fica claro onde está cada responsabilidade</li>
</ul>

<h2>🚀 Como Executar</h2>

<h3>Pré-requisitos</h3>
<ul>
  <li>Java 21</li>
  <li>Maven 3.6+</li>
</ul>

<h3>Executando a aplicação</h3>

<ol>
  <li><strong>Clone o repositório</strong>
    <pre>git clone https://github.com/felipelago/testeAgrotisBackend
cd agrotis-backend</pre>
  </li>
  
  <li><strong>Execute com Maven</strong>
    <pre>./mvnw spring-boot:run</pre>
  </li>
  
  <li><strong>Acesse a documentação da API</strong>
    <pre>http://localhost:8080/swagger-ui.html</pre>
  </li>
</ol>

<h3>Banco de Dados</h3>
<ul>
  <li>A aplicação utiliza <strong>H2 Database</strong> em memória</li>
  <li>Console H2: <code>http://localhost:8080/h2-console</code></li>
  <li>JDBC URL: <code>jdbc:h2:mem:testdb</code></li>
  <li>Username: <code>sa</code></li>
  <li>Password: <code>password</code></li>
</ul>

<h2>📋 Endpoints Principais</h2>

<h3>Laboratórios</h3>
<ul>
  <li><code>POST /laboratorios/v1/cadastrar</code> - Cadastrar laboratório</li>
  <li><code>GET /laboratorios/v1/listar/{id}</code> - Buscar por ID</li>
  <li><code>GET /laboratorios/v1/listar-laboratorios</code> - Listar todos</li>
  <li><code>GET /laboratorios/v1/listar-laboratorios-ativos</code> - Listar ativos (dropdown)</li>
  <li><code>PATCH /laboratorios/v1/inativar-laboratorio/{id}</code> - Inativar</li>
  <li><code>DELETE /laboratorios/v1/deletar/{id}</code> - Deletar um laboratório por ID</li>
</ul>

<h3>Propriedades</h3>
<ul>
  <li><code>POST /propriedades/v1/cadastrar</code> - Cadastrar propriedade</li>
  <li><code>GET /propriedades/v1/listar-propriedades</code> - Listar todas</li>
  <li><code>DELETE /propriedades/v1/deletar/{id}</code> - Deletar um propriedade por ID</li>
</ul>

<h3>Pessoas</h3>
<ul>
  <li><code>POST /pessoas/v1/cadastrar</code> - Cadastrar pessoa</li>
  <li><code>GET /pessoas/v1/listar-pessoas</code> - Listar todas</li>
  <li><code>DELETE /pessoas/v1/deletar/{id}</code> - Deletar uma pessoa por ID</li>
</ul>

<h3>Relatorio</h3>
<ul>
  <li><code>GET /api/laboratorios/v1/relatorio?quantidadeMinima=1&dataInicialStart=2022-02-02T17:41:44&dataInicialEnd=2022-02-02T17:41:44&observacoes=teste</code> - Exemplo de chamada com todos os parâmetros</li>
  <li><code>GET /api/laboratorios/v1/relatorio?quantidadeMinima=0</code> - Exemplo só com o obrigatório
</li>
</ul>

<h2>🧪 Exemplo de Request</h2>

<h3>Cadastrar Pessoa</h3>
Antes de cadastrar uma pessoa, você precisa cadastrar um Laboratório e uma Propriedade, pegar o ID de ambos e preencher no exemplo abaixo.

<pre>{
  "nome": "Jon Doe",
  "dataInicial": "2022-02-02T17:41:44Z",
  "dataFinal": "2022-02-02T17:41:44Z",
  "infosPropriedade": {
    "id": 1,
    "nome": "Nome Exemplo da fazenda"
  },
  "laboratorio": {
    "id": 1,
    "nome": "Laboratorio exemplo"
  },
  "observacoes": "Observacao exemplo de teste"
}</pre>

<h2>🛡️ Validações e Tratamento de Erros</h2>

<ul>
  <li><strong>Validação de entrada</strong>: Bean Validation com anotações</li>
  <li><strong>Tratamento global</strong>: <code>@RestControllerAdvice</code> para captura de exceções</li>
  <li><strong>Códigos HTTP apropriados</strong>: 400, 404, 409, 500</li>
  <li><strong>Mensagens padronizadas</strong>: Responses de erro estruturados</li>
</ul>

<h2>📦 Build e Deploy</h2>

<h3>Gerar JAR</h3>
<pre>./mvnw clean package</pre>

<h3>Executar JAR</h3>
<pre>java -jar target/agrotis-0.0.1-SNAPSHOT.jar</pre>

<h2>🤝 Contribuição</h2>

<p>Este projeto foi desenvolvido como teste técnico para a <strong>Agrotis Agroinformática LTDA</strong>.</p>

<h2>📝 Próximos Passos</h2>

<ul>
  <li>[ ] Corrigir e melhorar endpoint de relatório com filtros avançados</li>
  <li>[ ] Adicionar testes unitários</li>
  <li>[ ] Implementar cache para consultas frequentes</li>
  <li>[ ] Adicionar paginação nas listagens</li>
  <li>[ ] Adicionar uma camada Util para mapeamento de entidades e DTOs para melhor organização do projeto</li>
</ul>
