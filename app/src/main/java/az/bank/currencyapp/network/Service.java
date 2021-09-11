//package az.bank.currencyapp.network;
//
//import java.util.List;
//
//import az.bank.currencyapp.models.CurrencyResponse;
//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
//import rx.Observable;
//import rx.Subscriber;
//import rx.Subscription;
//import rx.functions.Func1;
//import rx.schedulers.Schedulers;
//
//public class Service {
//    private final NetworkService networkService;
//
//    public Service(NetworkService networkService) {
//        this.networkService = networkService;
//    }
//
//    public Subscription getCurrencyList(String from, String to, final GetCityListCallback callback) {
//
//        return networkService.getCurrency(from, to)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<CurrencyResponse>>>() {
//                    @Override
//                    public Observable<? extends List<CurrencyResponse>> call(Throwable throwable) {
//                        return Observable.error(throwable);
//                    }
//                })
//                .subscribe(new Subscriber<List<CurrencyResponse>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        callback.onError(new NetworkError(e));
//
//                    }
//
//                    @Override
//                    public void onNext(List<CurrencyResponse> cityListResponse) {
//                        callback.onSuccess(cityListResponse);
//
//                    }
//                });
//    }
//
//    public interface GetCityListCallback{
//        void onSuccess(List<CurrencyResponse> cityListResponse);
//
//        void onError(NetworkError networkError);
//    }
//}