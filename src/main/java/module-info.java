module com.tugalsan.api.math {
    requires commons.math3;
    requires com.tugalsan.api.tuple;
    requires com.tugalsan.api.list;
    requires com.tugalsan.api.log;
    requires com.tugalsan.api.string;
    requires com.tugalsan.api.stream;
    exports com.tugalsan.api.math.client;
    exports com.tugalsan.api.math.server;
}
