<h1>Agrotis Backend API Test</h1>
<p></p>
<hr/>
<h2>💡 Tecnologias utilizadas</h2>
<h3>Java 21 + Spring Boot 3.1.5</h3>
<ul>
    <li><strong>Spring Boot</strong>: framework robusto e produtivo para aplicações Java modernas.</li>
    <li><strong>Spring Web</strong>: para criação de endpoints REST.</li>
    <li><strong>Validation</strong>: para validação de inputs.</li>
    <li><strong>H2 Database</strong> - Banco de dados em memória para desenvolvimento</li>
    <li><strong>SpringDoc OpenAPI (Swagger)</strong> - Para documentação da API</li>
</ul>
<h2>🏗️ Arquitetura</h2>
<h3>Padrão Arquitetural</h3>
<ul>
  <li><strong>Arquitetura em Camadas (Layered Architecture).</strong></li>
  <li><strong>Padrão MVC</strong> na camada de apresentação.</li>
</ul>
<h3>Organização do Código</h3>
<p>O projeto utiliza organização por feature (package-by-feature) ao invés de package-by-layer</p>
<ul>
    <li><strong>Coesão e Acoplamento</strong></li>
    <li><strong>Navegação no Código</strong></li>
    <li><strong>Modificações e Manutenção</strong></li>
    <li><strong>Escalabilidade da Equipe</strong></li>
</ul>
<h3>Arquitetura Híbrida</h3>
O projeto implementa uma arquitetura híbrida com separação clara de responsabilidades:

Feature/
├── Controller (Apresentação)
├── Service (Lógica de Negócio)
├── Adapter (Infraestrutura)
├── Repository (Persistência)
├── Model (Entidades)
└── DTOs (Request/Response)