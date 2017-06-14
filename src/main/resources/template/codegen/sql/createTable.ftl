<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<hibernate-mapping>
	<class name="${table.tableName}" table="${table.tableName}" optimistic-lock="version">
		<#if table.columns?exists>
			<#list table.columns as attr>
				<#if attr.columnName == "id">
					<#if table.tablePKType?if_exists?html=='UUID'>
						<id name="id" type="java.lang.String" length="${attr.columnSize}" unsaved-value="null">
							<generator class="uuid" />
						</id>
					<#elseif table.tablePKType?if_exists?html=='NATIVE'>
						<#if dbType=='MYSQL'>
							<id name="id" type="java.lang.Long" length="${attr.columnSize}" unsaved-value="null">
								<generator class="identity" />
							</id>
						<#elseif dbType=='ORACLE'>
							<id name="id" type="java.lang.Long" length="${attr.columnSize}" unsaved-value="null">
								<generator class="native" />
							</id>
						<#elseif dbType=='SQLSERVER'>	
							<id name="id" type="java.lang.Long" length="${attr.columnSize}" unsaved-value="null">
								<generator class="identity" />
							</id>
						<#elseif dbType=='POSTGRESQL'>
							<id name="id" type="java.lang.Long" length="${attr.columnSize}" unsaved-value="null">
								<generator class="native" />
							</id>
						</#if>
					<#elseif table.tablePKType?if_exists?html=='SEQUENCE'>
						<#if dbType=='MYSQL'>
							<id name="id" type="java.lang.Long" length="${attr.columnSize}" unsaved-value="null">
								<generator class="identity" />
							</id>
						<#elseif dbType=='ORACLE'>
							<id name="id" type="java.lang.Long" length="${attr.columnSize}" unsaved-value="null">
								<generator class="sequence">
									<param name="sequence">${table.jformPkSequence}
									</param>
								</generator>
							</id>
						<#elseif dbType=='SQLSERVER'>	
							<id name="id" type="java.lang.Long" length="${attr.columnSize}" unsaved-value="null">
								<generator class="identity" />
							</id>
						<#elseif dbType=='POSTGRESQL'>
							<id name="id" type="java.lang.Long" length="${attr.columnSize}" unsaved-value="null">
								<generator class="native" />
							</id>
						</#if>	
					<#else>
						<id name="id" type="java.lang.String" length="${attr.columnSize}" unsaved-value="null">
							<generator class="uuid" />
						</id>
					</#if>
				</#if>
			</#list>
			<#list table.columns as attr>
				<#if attr.columnName != "id">
					<property name="${attr.columnName}"  type="${attr.hibernateType}" access="property">
						<column name="${attr.columnName}" <#if attr.javaType=='Double'||attr.javaType=='BigDecimal'>
							precision="${attr.columnSize}" scale="${attr.decimalDigits}"<#else>length="${attr.columnSize}"</#if>
							<#if attr.columnDef?exists&&attr.columnDef!=''>default="${attr.columnDef}"</#if>
							not-null="<#if attr.nullable>false<#else>true</#if>" unique="false">
							<comment>${attr.remarks}</comment>
						</column>
					</property>
				</#if>
			</#list>
		</#if>
	</class>
</hibernate-mapping>