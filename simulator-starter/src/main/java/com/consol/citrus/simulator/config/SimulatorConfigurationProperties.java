/*
 * Copyright 2006-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.consol.citrus.simulator.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * @author Christoph Deppisch
 */
@ConfigurationProperties(prefix = "citrus.simulator")
public class SimulatorConfigurationProperties implements EnvironmentAware {

    /** Logger */
    private static Logger log = LoggerFactory.getLogger(SimulatorConfigurationProperties.class);

    /**
     * Global option to enable/disable simulator support, default is true.
     */
    private boolean enabled = true;

    /** Template path */
    private static final String SIMULATOR_TEMPLATE_PATH_PROPERTY = "citrus.simulator.template.path";
    private static final String SIMULATOR_TEMPLATE_PATH_ENV = "CITRUS_SIMULATOR_TEMPLATE_PATH";
    private String templatePath = "com/consol/citrus/simulator/templates";

    /** Default test scenario chosen in case of unknown scenario */
    private static final String SIMULATOR_SCENARIO_PROPERTY = "citrus.simulator.default.scenario";
    private static final String SIMULATOR_SCENARIO_ENV = "CITRUS_SIMULATOR_DEFAULT_SCENARIO";
    private String defaultScenario = "DEFAULT_SCENARIO";

    /** Default timeout when waiting for incoming messages */
    private static final String SIMULATOR_TIMEOUT_PROPERTY = "citrus.simulator.default.timeout";
    private static final String SIMULATOR_TIMEOUT_ENV = "CITRUS_SIMULATOR_DEFAULT_TIMEOUT";
    private Long defaultTimeout = 5000L;

    /** Property that en-/disables template validation, default value is true */
    private static final String SIMULATOR_TEMPLATE_VALIDATION_PROPERTY = "citrus.simulator.template.validation";
    private static final String SIMULATOR_TEMPLATE_VALIDATION_ENV = "CITRUS_SIMULATOR_TEMPLATE_VALIDATION";
    private boolean templateValidation = true;

    /** Default delay in milliseconds to wait after uncategorized exceptions */
    private static final String SIMULATOR_EXCEPTION_DELAY_PROPERTY = "citrus.simulator.exception.delay";
    private static final String SIMULATOR_EXCEPTION_DELAY_ENV = "CITRUS_SIMULATOR_EXCEPTION_DELAY";
    private Long exceptionDelay = 5000L;

    /** Optional inbound/outbound data dictionary mapping file for generated test data */
    private static final String SIMULATOR_INBOUND_XML_DICTIONARY_PROPERTY = "citrus.simulator.inbound.xml.dictionary";
    private static final String SIMULATOR_INBOUND_XML_DICTIONARY_ENV = "CITRUS_SIMULATOR_INBOUND_XML_DICTIONARY";
    private String inboundXmlDictionary = "inbound-xml-dictionary.properties";

    private static final String SIMULATOR_OUTBOUND_XML_DICTIONARY_PROPERTY = "citrus.simulator.outbound.xml.dictionary";
    private static final String SIMULATOR_OUTBOUND_XML_DICTIONARY_ENV = "CITRUS_SIMULATOR_OUTBOUND_XML_DICTIONARY";
    private String outboundXmlDictionary = "outbound-xml-dictionary.properties";

    /**
     * The Spring application context environment
     */
    private Environment env;

    @PostConstruct
    private void loadProperties() {
        templatePath = env.getProperty(SIMULATOR_TEMPLATE_PATH_PROPERTY, env.getProperty(SIMULATOR_TEMPLATE_PATH_ENV, templatePath));
        defaultScenario = env.getProperty(SIMULATOR_SCENARIO_PROPERTY, env.getProperty(SIMULATOR_SCENARIO_ENV, defaultScenario));
        defaultTimeout = Long.valueOf(env.getProperty(SIMULATOR_TIMEOUT_PROPERTY, env.getProperty(SIMULATOR_TIMEOUT_ENV, String.valueOf(defaultTimeout))));
        templateValidation = Boolean.valueOf(env.getProperty(SIMULATOR_TEMPLATE_VALIDATION_PROPERTY, env.getProperty(SIMULATOR_TEMPLATE_VALIDATION_ENV, String.valueOf(templateValidation))));
        exceptionDelay = Long.valueOf(env.getProperty(SIMULATOR_EXCEPTION_DELAY_PROPERTY, env.getProperty(SIMULATOR_EXCEPTION_DELAY_ENV, String.valueOf(exceptionDelay))));
        inboundXmlDictionary = env.getProperty(SIMULATOR_INBOUND_XML_DICTIONARY_PROPERTY, env.getProperty(SIMULATOR_INBOUND_XML_DICTIONARY_ENV, inboundXmlDictionary));
        outboundXmlDictionary = env.getProperty(SIMULATOR_OUTBOUND_XML_DICTIONARY_PROPERTY, env.getProperty(SIMULATOR_OUTBOUND_XML_DICTIONARY_ENV, outboundXmlDictionary));

        log.info("Using the simulator configuration: {}", this.toString());
    }

    /**
     * Gets the enabled.
     *
     * @return
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the enabled.
     *
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Gets the template path property.
     *
     * @return
     */
    public String getTemplatePath() {
        return templatePath;
    }

    /**
     * Sets the template path property.
     *
     * @param templatePath
     */
    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    /**
     * Gets the default scenario name.
     *
     * @return
     */
    public String getDefaultScenario() {
        return defaultScenario;
    }

    /**
     * Sets the default scenario name.
     *
     * @param defaultScenario
     */
    public void setDefaultScenario(String defaultScenario) {
        this.defaultScenario = defaultScenario;
    }

    /**
     * Checks System property on template validation setting. By default enabled (e.g. if System property is not set).
     *
     * @return
     */
    public boolean isTemplateValidation() {
        return templateValidation;
    }

    /**
     * En- or disables the template validation.
     *
     * @param templateValidation
     */
    public void setTemplateValidation(boolean templateValidation) {
        this.templateValidation = templateValidation;
    }

    /**
     * Sets the default timeout property.
     *
     * @param timout
     */
    public void setDefaultTimeout(Long timout) {
        this.defaultTimeout = timout;
    }

    /**
     * Gets the default timeout property.
     *
     * @return
     */
    public Long getDefaultTimeout() {
        return defaultTimeout;
    }

    /**
     * Gets the exceptionDelay.
     *
     * @return
     */
    public Long getExceptionDelay() {
        return exceptionDelay;
    }

    /**
     * Sets the exceptionDelay.
     *
     * @param exceptionDelay
     */
    public void setExceptionDelay(Long exceptionDelay) {
        this.exceptionDelay = exceptionDelay;
    }

    /**
     * Gets the inboundXmlDictionary.
     *
     * @return
     */
    public String getInboundXmlDictionary() {
        return inboundXmlDictionary;
    }

    /**
     * Sets the inboundXmlDictionary.
     *
     * @param inboundXmlDictionary
     */
    public void setInboundXmlDictionary(String inboundXmlDictionary) {
        this.inboundXmlDictionary = inboundXmlDictionary;
    }

    /**
     * Gets the outboundXmlDictionary.
     *
     * @return
     */
    public String getOutboundXmlDictionary() {
        return outboundXmlDictionary;
    }

    /**
     * Sets the outboundXmlDictionary.
     *
     * @param outboundXmlDictionary
     */
    public void setOutboundXmlDictionary(String outboundXmlDictionary) {
        this.outboundXmlDictionary = outboundXmlDictionary;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "enabled='" + enabled + '\'' +
                ", templatePath='" + templatePath + '\'' +
                ", defaultScenario='" + defaultScenario + '\'' +
                ", defaultTimeout=" + defaultTimeout +
                ", exceptionDelay=" + exceptionDelay +
                ", templateValidation=" + templateValidation +
                ", inboundXmlDictionary=" + inboundXmlDictionary +
                ", outboundXmlDictionary=" + outboundXmlDictionary +
                '}';
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }
}
