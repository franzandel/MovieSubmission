function copyEnvVarsToLocalProperties {
    FAKE_LOCAL_PROPERTIES=./local.properties
    export FAKE_LOCAL_PROPERTIES
    echo "Local Properties should exist at $FAKE_LOCAL_PROPERTIES"

    if [ ! -f "$FAKE_LOCAL_PROPERTIES" ]; then
        echo "Local Properties does not exist"

        echo "Creating Fake Local Properties file..."
        touch $FAKE_LOCAL_PROPERTIES

        echo "Writing API KEY to Fake local.properties..."
        echo "movie_db_api_key=$MOVIE_DB_API_KEY_ENV_VAR" >> $FAKE_LOCAL_PROPERTIES
        echo "cert_pinner_1=$CERT_PINNER_1_ENV_VAR" >> $FAKE_LOCAL_PROPERTIES
        echo "cert_pinner_2=$CERT_PINNER_2_ENV_VAR" >> $FAKE_LOCAL_PROPERTIES
        echo "cert_pinner_3=$CERT_PINNER_3_ENV_VAR" >> $FAKE_LOCAL_PROPERTIES
        echo "cert_pinner_4=$CERT_PINNER_4_ENV_VAR" >> $FAKE_LOCAL_PROPERTIES

        echo "Display Content Fake Local Properties"
        cat $FAKE_LOCAL_PROPERTIES
    fi
}