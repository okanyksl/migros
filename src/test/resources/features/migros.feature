Feature: Migros Pet Shop Otomasyonu

@Test
  Scenario: Pet Shop sayfasındaki ürünler düşük fiyata göre sıralanır
    Given Migros websitesini açarım
    When "Pet Shop" kategorisine giderim
    Then Pet Shop sayfasının açıldığını doğrularım
    When Ürünleri düşük fiyattan yükseğe doğru sıralarım
    Then Ürünlerin fiyatlara göre sıralandığını doğrularım    