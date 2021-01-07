/**
 * Created by root on 2017/4/19.
 */
//加密
function rsajm(data) {
    var pubkey='MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDlOttJmW8I8dpupzxKGYJbfAtTrijZkdsT+6BnYZV6DHf+YOutAag7UW1SeuK9OMmckO+RaoK+gD8Mvyri7xLcDk6t /eOQ0s7POq9/1hMDYgkF/kP6EPTtyK1D7r57Ycwlo5XtWd+HcsAbZklyJs25ep3bzxThTgnU2kM85iOXfwIDAQAB';
    var encrypt = new JSEncrypt();
    encrypt.setPublicKey(pubkey);
    var encrypted = encrypt.encrypt(data);
    return encrypted;
}
//解密
function rsa_jm(data) {
    var privkey='MIICXAIBAAKBgQDVICVTwFdCWMHJmBiklV28SeJb/5Crj6VdYxHL6fOdejzzKL6DdnsyGs/TmZuA0rdS2EGFyxcYUTWdm5xRKP2W7N87m+7Z3LGnPI7bF2/B+CC1ZAzX5bGiFhmOEnWf1Ji/zg+vy9y8vNRAOXscdAumvLPNBK4Z8tZNeW1rQqBE2wIDAQABAoGABFkQieHdpdvbsDorHohxjhrPxGK1V9qpW/tebJOMRcUtoyRMpq/vGXmUjdOPaZRi6wzAYXNgOD3H4zw+dyVn7vfBEL4nbBa6wQMDtvNis8GGjg0ZEYtdVGUCFDThxEhuTOs5l0TTx+Sj0YtC0LsUMQUQZQIGemEO3wmxhSKLNqECQQD17tSd4TPZrPaMZrpUE3nYwoCuXHZLGqjsn/I5N4H9iFrIgxd6C+Y7VOgPlTRt5WKnmxnGz29fjxSuUtUv66RAkEA3dmFlg04E7TlMjg36Ye4F0Q/HaLa6kRNiYEI/vfQcm3ERlxwy8ZcQ+XwXq0KUWnWUbbX7JeNXmIzeI9lCfUKqwJAMUZwDU4RAH06TPlMPgpE8JxQAh9+ iB/IVtjZRIONpQnL/r7dVlPKBntFn9GqVpaORLy71my7iO73HRs2L73UMQJBAKNgGG+LZpukoREds5dIOnrv6Gcj0oGpMUys+PoDd799NIj4dJyshK//+sdOOYYBIjLlKxhx90rlmLAVMbxmd6cCQD/BeXRia62RKLE/R5maHT+rxKaggiLAF1vNDcOU8rQgUHtFuCr4GU69Pktw8/fKizfA3Ze2jgub4cLfg2fEwrs=';
    var decrypt = new JSEncrypt();
    decrypt.setPrivateKey(privkey);
    var uncrypted = decrypt.decrypt(data);
    return uncrypted;
}

var site_url='http://www.xhl.com';
