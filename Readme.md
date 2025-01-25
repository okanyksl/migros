## Kurulum

1. **Maven**'in sisteminizde kurulu olduğundan emin olun.
2. Projeyi klonlayın veya indirin.
3. Terminal veya komut istemcisinde proje dizinine gidin.
4. Aşağıdaki komutu çalıştırarak bağımlılıkları yükleyin:

   ```
   mvn clean install
   ```

## Test Senaryoları

Proje, Migros Pet Shop sayfasındaki ürünlerin düşük fiyata göre sıralanmasını test eden bir senaryo içermektedir. Senaryo, `migros.feature` dosyasında tanımlanmıştır.

## Kullanım

Testleri çalıştırmak için aşağıdaki komutu kullanabilirsiniz. Aşağıdaki komut, default browser olarak Chrome üzerinde testi çalıştıracaktır.

```
mvn clean test
```

Browser bilgisini parametrik olarak geçmek için -Dbrowser parametresi kullanılmalıdır. Aşağıda kullanım örneği mevcuttur.

```
mvn clean test -Dbrowser=chrome
```

```
mvn clean test -Dbrowser=firefox
```

```
mvn clean test -Dbrowser=edge
```

